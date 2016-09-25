package com.manofj.commons.minecraftforge.network

import scala.reflect.ClassTag

import net.minecraft.entity.player.EntityPlayerMP

import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side

import com.manofj.commons.minecraftforge.base.MinecraftForgeMod


/** シンプルなネットワークチャネルを持つMod
  */
trait SimpleNetworkMod {
  this: MinecraftForgeMod =>


  private[ this ] val discriminatorMaker = Iterator.iterate( 0 )( _ + 1 )


  /** シンプルな実装のネットワークチャネル
    */
  protected val network: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel( this.modId )


  /** 自身のチャネルに対応可能なメッセージを登録する
    *
    * @param handler メッセージのハンドラ
    * @param sides   メッセージを受信可能なサイド
    */
  protected def registerMessage[ A <: IMessage, B <: IMessage ]( handler: MessageHandler[ A, B ], sides: Side* )
                                                               ( implicit tag: ClassTag[ A ] ) =
    sides.foreach( network.registerMessage[ A, B ]( handler, handler.requestMessageClass, discriminatorMaker.next, _ ) )


  /** 指定されたプレイヤーにメッセージを送信する
    * メッセージのハンドラがクライアントサイドに登録されている必要がある
    *
    * @param message 送信するメッセージ
    * @param player  メッセージを受信するプレイヤー
    */
  def sendTo( message: IMessage, player: EntityPlayerMP ): Unit = network.sendTo( message, player )

  /** すべてのプレイヤーにメッセージを送信する
    * メッセージのハンドラがクライアントサイドに登録されている必要がある
    *
    * @param message 送信するメッセージ
    */
  def sendToAll( message: IMessage ): Unit = network.sendToAll( message )

  /** 指定された範囲内の全プレイヤーにメッセージを送信する
    * メッセージのハンドラがクライアントサイドに登録されている必要がある
    *
    * @param message 送信するメッセージ
    * @param point   メッセージを送信する範囲
    */
  def sendToAllAround( message: IMessage, point: TargetPoint ): Unit = network.sendToAllAround( message, point )

  /** 指定されたディメンション内の全プレイヤーにメッセージを送信する
    * メッセージのハンドラがクライアントサイドに登録されている必要がある
    *
    * @param message     送信するメッセージ
    * @param dimensionId 対象ディメンションのID
    */
  def sendToDimension( message: IMessage, dimensionId: Int ): Unit = network.sendToDimension( message, dimensionId )

  /** メッセージをサーバに送信する
    * メッセージのハンドラがサーバサイドに登録されている必要がある
    *
    * @param message 送信するメッセージ
    */
  def sendToServer( message: IMessage ): Unit = network.sendToServer( message )

}
