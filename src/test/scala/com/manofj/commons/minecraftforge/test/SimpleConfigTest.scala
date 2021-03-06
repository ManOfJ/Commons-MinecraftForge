package com.manofj.commons.minecraftforge.test

import net.minecraft.client.gui.GuiScreen

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

import com.manofj.commons.minecraftforge.base.MinecraftForgeMod
import com.manofj.commons.minecraftforge.config.ForgeConfig
import com.manofj.commons.minecraftforge.config.SimpleConfig
import com.manofj.commons.minecraftforge.config.gui.ConfigGui
import com.manofj.commons.minecraftforge.config.gui.SimpleGuiFactory
import com.manofj.commons.minecraftforge.config.gui.SimpleGuiParams


/** コンフィグテンプレートのテスト
  */
@Mod( modid       = SimpleConfigTest.modId,
      name        = SimpleConfigTest.modName,
      version     = SimpleConfigTest.modVersion,
      modLanguage = SimpleConfigTest.modLanguage,
      guiFactory  = "com.manofj.commons.minecraftforge.test.TestFactory" )
object SimpleConfigTest
  extends MinecraftForgeMod
{

  override final val modId      = "simpleConfigTest"
  override final val modName    = "SimpleConfigTest"
  override final val modVersion = "0.0.0"


  final val ENABLE = false


  @Mod.EventHandler
  def preInit( e: FMLPreInitializationEvent ): Unit =
    if ( ENABLE ) {
      e.getModMetadata.autogenerated = false
      TestConfig.capture( e.getSuggestedConfigurationFile )
      MinecraftForge.EVENT_BUS.register( TestConfig )
    }

}

object TestConfig
  extends SimpleConfig
  with    SimpleGuiParams
{

  override val modId: String = "configTest"
  override val configId: String = super.configId


  override protected def config: ForgeConfig = theConfig


  override def title: String = "ConfigGUI Test"

  override def sync(): Unit = {
    val cfg = theConfig

    val p1 = cfg.get( configId, "test_1", 1 )
    println( "test_1 = " + p1.getInt )

    val p2 = cfg.get( configId, "test_2", false )
    println( "test_2 = " + p2.getBoolean )

    val p3 = cfg.get( configId, "test_3", Array( 1.0, 2.0, 3.0 ) )
    println( "test_3 = " + p3.getDoubleList.mkString( "[", ", ", "]" ) )

  }

}

class TestFactory extends SimpleGuiFactory[ TestGui ]
class TestGui( p: GuiScreen ) extends ConfigGui( p, TestConfig )
