package com.manofj.commons.minecraftforge.i18n

import net.minecraft.client.resources.I18n

import com.manofj.commons.minecraftforge.base.MinecraftForgeMod


/** 国際化に対応するMod
  */
trait I18nSupportMod
  extends I18nSupport
{
  this: MinecraftForgeMod =>


  override def languageKey( base: String, prefix: String = "", suffix: String = "" ): String = {
    val sb = StringBuilder.newBuilder

    sb ++= this.modId
    if ( prefix.nonEmpty ) sb += '.' ++= prefix
    if ( base.nonEmpty )   sb += '.' ++= base
    if ( suffix.nonEmpty ) sb += '.' ++= suffix

    sb.mkString
  }

  override def hasKey( key: String ): Boolean = I18n.hasKey( key )

  override def message( key: String, args: AnyRef* ): String = I18n.format( key, args: _* )

}
