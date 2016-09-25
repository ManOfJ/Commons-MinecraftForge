package com.manofj.commons.minecraftforge.config

import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

import com.manofj.commons.scala.alias.java.IO.JavaFile


/** Modのコンフィグ
  * [[net.minecraftforge.common.config.Configuration]] をフィールドとして持ち､
  * この設定項目に外部からアクセスできるように実装をおこなう
  */
trait Config {

  protected def modId: String
  protected def configId: String
  protected def configVersion: String


  /** バインドされているコンフィグオブジェクトを返す
    */
  def theConfig: ForgeConfig


  /** [[net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent]] のリスナーメソッド
    * 動作させるには [[net.minecraftforge.common.MinecraftForge#EVENT_BUS]] への登録が必要
    */
  @SubscribeEvent
  def onConfigChanged( event: OnConfigChangedEvent ): Unit =
    if ( event.getModID    == modId &&
         event.getConfigID == configId )
    {
      sync()
      save()
    }


  /** コンフィグのパスからオブジェクトを作成し､バインドする
    */
  def capture( configPath: JavaFile ): Unit = capture( new ForgeConfig( configPath, configVersion ) )

  /** コンフィグのロードを行う
    */
  def load(): Unit = {
    val cfg = theConfig

    if ( !cfg.isChild ) {
      cfg.load()

      Option( cfg.getDefinedConfigVersion ) match {
        case None => // do nothing
        case Some( x ) => Option( cfg.getLoadedConfigVersion ) match {
          case Some( y ) => if ( x != y ) fix( x, y )
          case None      => fix( x, "" )
        }
      }
    }
  }

  /** コンフィグのセーブを行う
    */
  def save(): Unit = if ( theConfig.hasChanged ) theConfig.save()


  /** コンフィグオブジェクトをバインドする
    */
  def capture( config: ForgeConfig ): Unit

  /** コンフィグのバージョンによる差異を修正する
    *
    * @param definedVersion コンフィグオブジェクトに定義されているバージョン
    * @param loadedVersion  ロードされたコンフィグのバージョン
    */
  def fix( definedVersion: String, loadedVersion: String ): Unit

  /** コンフィグの同期を行う
    */
  def sync(): Unit

}
