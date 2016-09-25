package com.manofj.commons.minecraftforge.network.io

import io.netty.buffer.ByteBufInputStream
import io.netty.buffer.ByteBufOutputStream


/** ネットワークのIOに関する暗黙の変換クラスを定義する
  */
package object conversions {

  implicit class ByteBufInputStream$( protected val self: ByteBufInputStream ) extends AnyVal with ByteBufInputStreamExtension

  implicit class ByteBufOutputStream$( protected val self: ByteBufOutputStream ) extends AnyVal with ByteBufOutputStreamExtension

}
