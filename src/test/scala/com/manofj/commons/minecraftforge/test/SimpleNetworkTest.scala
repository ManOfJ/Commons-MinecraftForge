package com.manofj.commons.minecraftforge.test

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.UUID

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufInputStream
import io.netty.buffer.ByteBufOutputStream
import org.lwjgl.input.Keyboard

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompressedStreamTools

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import net.minecraftforge.fml.relauncher.Side

import com.manofj.commons.minecraftforge.base.MinecraftForgeMod
import com.manofj.commons.minecraftforge.network.MessageHandler
import com.manofj.commons.minecraftforge.network.SimpleNetworkMod
import com.manofj.commons.minecraftforge.network.io.ByteBufReadFunction
import com.manofj.commons.minecraftforge.network.io.ByteBufWriteFunction
import com.manofj.commons.minecraftforge.network.io.conversions.ByteBufInputStream$
import com.manofj.commons.minecraftforge.network.io.conversions.ByteBufOutputStream$


/** シンプルネットワークテンプレートのテスト
  */
@Mod( modid       = SimpleNetworkTest.modId,
      name        = SimpleNetworkTest.modName,
      version     = SimpleNetworkTest.modVersion,
      modLanguage = SimpleNetworkTest.modLanguage )
object SimpleNetworkTest
  extends MinecraftForgeMod
  with    SimpleNetworkMod
{

  override final val modId      = "simpleNetworkTest"
  override final val modName    = "SimpleNetworkTest"
  override final val modVersion = "0.0.0"


  final val ENABLE = false


  @Mod.EventHandler
  def preInit( event: FMLPreInitializationEvent ): Unit =
    if ( ENABLE ) {
      MinecraftForge.EVENT_BUS.register( this )
      registerMessage( TestMessage, Side.CLIENT )
      registerMessage( TestMessage2, Side.values: _* )
    }

  @SubscribeEvent
  def pickup( event: ItemPickupEvent ): Unit = {
    event.player match {
      case mp: EntityPlayerMP =>
        for { world <- Option( mp.worldObj ) if !world.isRemote } network.sendTo( TestMessage.INSTANCE, mp )
      case _ =>
    }
  }

  @SubscribeEvent
  def keyInput( event: KeyInputEvent ): Unit = {
    if ( Keyboard.isKeyDown( Keyboard.KEY_SPACE ) ) {
      network.sendToServer( new TestMessage2() )
    }
  }

}

class TestMessage private()
  extends IMessage
{
  override def toBytes( buf: ByteBuf ): Unit = {}
  override def fromBytes( buf: ByteBuf ): Unit = {}
}

object TestMessage
  extends MessageHandler[ TestMessage, IMessage ]
{
  final val INSTANCE = new TestMessage()

  override def onMessage( message: TestMessage, ctx: MessageContext ): IMessage = {
    println( s"${ ctx.side }: TestMessage" )
    null
  }
}

class TestMessage2
  extends IMessage
{

  implicit final val readItemStack = ByteBufReadFunction { x =>
    val data = new ByteArrayInputStream( x.readBinary )
    val tag = CompressedStreamTools.readCompressed( data )

    ItemStack.loadItemStackFromNBT( tag )
  }

  implicit final val writeItemStack = ByteBufWriteFunction { ( out, item: ItemStack ) =>
    val data = new ByteArrayOutputStream()
    CompressedStreamTools.writeCompressed( item.serializeNBT, data )
    val bin = data.toByteArray

    out.writeInt( bin.length )
    out.write( bin )
  }


  var uuid = Option.empty[ UUID ]
  var item = Option.empty[ ItemStack ]


  def this( player: EntityPlayer, item: ItemStack = null ) = {
    this()
    this.uuid = Option( player.getUniqueID )
    this.item = Option( item )
  }

  override def toBytes( buf: ByteBuf ): Unit = {
    val writer = new ByteBufOutputStream( buf )

    writer.writeOption( uuid )
    writer.writeOption( item )
  }

  override def fromBytes( buf: ByteBuf ): Unit = {
    val input = new ByteBufInputStream( buf )

    uuid = input.readOption[ UUID ]
    item = input.readOption[ ItemStack ]
  }

}

object TestMessage2
  extends MessageHandler[ TestMessage2, TestMessage2 ]
{
  override def onMessage( message: TestMessage2, ctx: MessageContext ): TestMessage2 = {
    ctx.side match {
      case Side.SERVER =>
        val player = ctx.getServerHandler.playerEntity

        new TestMessage2( player, new ItemStack( Items.APPLE ) )

      case Side.CLIENT =>
        val server = FMLCommonHandler.instance.getMinecraftServerInstance
        message.uuid.map( server.getPlayerList.getPlayerByUUID )
          .zip( message.item )
          .foreach( x => x._1.inventory.addItemStackToInventory( x._2 ) )

        null
    }
  }
}
