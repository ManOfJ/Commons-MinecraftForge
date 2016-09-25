package com.manofj.commons.minecraftforge.network.io.conversions

import io.netty.buffer.ByteBufInputStream

import com.manofj.commons.scala.util.conversions.Extension

import com.manofj.commons.minecraftforge.network.io.ByteBufReadFunction


/** $input のインスタンスに対して､ユーティリティメソッドを使用可能にする
  *
  * @define p      `self`
  * @define input  [[io.netty.buffer.ByteBufInputStream]]
  */
trait ByteBufInputStreamExtension
  extends Any
  with    Extension[ ByteBufInputStream ]
{

  /** $p からオブジェクトを読み込む
    */
  def read[ A ]( implicit function: ByteBufReadFunction[ A ] ): A = function( self )

  /** $p からバイト配列を読み込む
    */
  def readBinary: Array[ Byte ] = {
    val bin = new Array[ Byte ]( self.readInt() )
    self.read( bin )
    bin
  }

  /** $p からオブジェクトのコレクションを読み込む
    */
  def readCollection[ A ]( implicit function: ByteBufReadFunction[ A ] ): Iterable[ A ] =
    ( 0 until self.readInt() ).map( _ => function( self ) )

  /** $p からオプショナルオブジェクトを読み込む
    */
  def readOption[ A ]( implicit function: ByteBufReadFunction[ A ] ): Option[ A ] =
    if ( self.readBoolean() ) Option( function( self ) ) else None

}
