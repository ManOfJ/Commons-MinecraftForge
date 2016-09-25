package com.manofj.commons.minecraftforge.input

import java.util.Locale

import org.lwjgl.input.Keyboard

import net.minecraftforge.client.settings.KeyModifier

import com.manofj.commons.scala.util.conversions.Boolean$


/** キーボードに関するユーティリティメソッドを提供する
  */
object KeyboardHelper {

  /** 指定されたインデックスが有効なものであれば  `true`
    * そうでなければ `false` を返す
    */
  def isValidIndex( index: Int ): Boolean = index >= 0 && index < Keyboard.KEYBOARD_SIZE

  /** 指定された名前に対応するキーのインデックスを返す
    * 対応するキーが存在しない場合は [[org.lwjgl.input.Keyboard#KEY_NONE]] を返す
    */
  def nameByIndex( name: String ): Int = Keyboard.getKeyIndex( name )

  /** 指定されたインデックスに対応するキーの名前を返す
    * 対応するキーが存在しない場合は文字列 `NONE` を返す
    */
  def indexByName( index: Int ): String =
    isValidIndex( index ) ? Keyboard.getKeyName( index ) ! Keyboard.getKeyName( Keyboard.KEY_NONE )

  /** 指定された名前に対応する修飾キーを返す
    * 対応するキーが存在しない場合は [[net.minecraftforge.client.settings.KeyModifier#NONE]] を返す
    */
  def keyModifier( name: String ): KeyModifier =
    name.toLowerCase( Locale.ENGLISH ) match {
      case "ctrl" | "control" => KeyModifier.CONTROL
      case "alt"              => KeyModifier.ALT
      case "shift"            => KeyModifier.SHIFT
      case _                  => KeyModifier.NONE
    }

}
