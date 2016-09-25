package com.manofj.commons.minecraftforge.network.io

import java.util.UUID

import io.netty.buffer.ByteBufInputStream


/** デフォルトで提供される読み込み関数を定義する
  */
object ByteBufReadFunction {

  trait UUIDReadFunction extends ByteBufReadFunction[ UUID ] { override def apply( v: ByteBufInputStream ): UUID = new UUID( v.readLong(), v.readLong() ) }
  implicit object UUID extends UUIDReadFunction


  def apply[ A ]( function: ByteBufInputStream => A ): ByteBufReadFunction[ A ] =
    new ByteBufReadFunction[A] { override def apply( v1: ByteBufInputStream ): A = function( v1 ) }

}

/** [[io.netty.buffer.ByteBufInputStream]] のオブジェクト読み込み関数
  */
trait ByteBufReadFunction[ A ] extends Function[ ByteBufInputStream, A ]
