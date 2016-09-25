package com.manofj.commons.minecraftforge.logging


/** ロガーを模したメソッドを持つ
  */
trait LoggerLike {

  def debug( msg: String ): Unit
  def debug( msg: String, args: AnyRef* ): Unit
  def debug( msg: String, error: Throwable ): Unit

  def error( msg: String ): Unit
  def error( msg: String, args: AnyRef* ): Unit
  def error( msg: String, error: Throwable ): Unit

  def fatal( msg: String ): Unit
  def fatal( msg: String, args: AnyRef* ): Unit
  def fatal( msg: String, error: Throwable ): Unit

  def info( msg: String ): Unit
  def info( msg: String, args: AnyRef* ): Unit
  def info( msg: String, error: Throwable ): Unit

  def trace( msg: String ): Unit
  def trace( msg: String, args: AnyRef* ): Unit
  def trace( msg: String, error: Throwable ): Unit

  def warn( msg: String ): Unit
  def warn( msg: String, args: AnyRef* ): Unit
  def warn( msg: String, error: Throwable ): Unit

}
