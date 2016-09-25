package com.manofj.commons.minecraftforge.network.io

import java.util.UUID

import io.netty.buffer.ByteBufOutputStream


/** デフォルトで提供される書き込み関数を定義する
  */
object ByteBufWriteFunction {

  trait UUIDWriteFunction extends ByteBufWriteFunction[ UUID ] {
    override def apply( v: ( ByteBufOutputStream, UUID ) ): Unit = {
      val ( out, uuid ) = v
      out.writeLong( uuid.getMostSignificantBits )
      out.writeLong( uuid.getLeastSignificantBits )
    }
  }
  implicit object UUID extends UUIDWriteFunction


  def apply[ A ]( function: ( ByteBufOutputStream, A ) => Unit ): ByteBufWriteFunction[ A ] =
    new ByteBufWriteFunction[ A ] { override def apply( v: ( ByteBufOutputStream, A ) ): Unit = function( v._1, v._2 ) }

}

/** [[io.netty.buffer.ByteBufOutputStream]] のオブジェクト書き込み関数
  */
trait ByteBufWriteFunction[ A ] extends Function[ ( ByteBufOutputStream, A ), Unit ]
