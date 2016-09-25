package com.manofj.commons.minecraftforge.config.gui

import net.minecraftforge.fml.client.config.IConfigElement

import com.manofj.commons.scala.alias.java.Collection.JavaList


/** コンフィグGUIのコンテンツを実装する
  */
trait GuiParams {

  /** コンフィグ項目の操作を行うオブジェクトのリストを返す
    */
  def configElements: JavaList[ IConfigElement ]

  /** コンフィグの親となるModのIDを返す
    */
  def modId: String

  /** GUIが操作するコンフィグのIDを返す
    */
  def configId: String

  /** コンフィグに変更があった際にワールドの再起動が必要であるか否か
    */
  def allRequireWorldRestart: Boolean

  /** コンフィグに変更があった際にMinecraftの再起動が必要であるか否か
    */
  def allRequireMcRestart: Boolean

  /** GUIに表示されるタイトル文字列
    */
  def title: String

  /** GUIに表示されるタイトル文字列の二行目
    */
  def titleLine2: String

}
