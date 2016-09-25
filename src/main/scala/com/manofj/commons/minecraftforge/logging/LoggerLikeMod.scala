package com.manofj.commons.minecraftforge.logging

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import com.manofj.commons.minecraftforge.base.MinecraftForgeMod


/** ログメッセージを受け取ることが可能なMod
  */
trait LoggerLikeMod
  extends LoggerLike
{
  this: MinecraftForgeMod =>


  protected val logger: Logger = LogManager.getLogger( this.modId )


  override def debug( msg: String ): Unit = logger.debug( msg )
  override def debug( msg: String, args: AnyRef* ): Unit = logger.debug( msg, args: _* )
  override def debug( msg: String, error: Throwable ): Unit = logger.debug( msg, error )

  override def error( msg: String ): Unit = logger.error( msg )
  override def error( msg: String, args: AnyRef* ): Unit = logger.error( msg, args: _* )
  override def error( msg: String, error: Throwable ): Unit = logger.error( msg, error )

  override def fatal( msg: String ): Unit = logger.fatal( msg )
  override def fatal( msg: String, args: AnyRef* ): Unit = logger.fatal( msg, args: _* )
  override def fatal( msg: String, error: Throwable ): Unit = logger.fatal( msg, error )

  override def info( msg: String ): Unit = logger.info( msg )
  override def info( msg: String, args: AnyRef* ): Unit = logger.info( msg, args: _* )
  override def info( msg: String, error: Throwable ): Unit = logger.info( msg, error )

  override def trace( msg: String ): Unit = logger.trace( msg )
  override def trace( msg: String, args: AnyRef* ): Unit = logger.trace( msg, args: _* )
  override def trace( msg: String, error: Throwable ): Unit = logger.trace( msg, error )

  override def warn( msg: String ): Unit = logger.warn( msg )
  override def warn( msg: String, args: AnyRef* ): Unit = logger.warn( msg, args: _* )
  override def warn( msg: String, error: Throwable ): Unit = logger.warn( msg, error )

}
