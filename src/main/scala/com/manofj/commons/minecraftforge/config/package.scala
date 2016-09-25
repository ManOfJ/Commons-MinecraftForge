package com.manofj.commons.minecraftforge


/** 所属するクラスのためのエイリアス､定数を提供する
  */
package object config {

  type ForgeConfig   = net.minecraftforge.common.config.Configuration
  type ForgeCategory = net.minecraftforge.common.config.ConfigCategory
  type ForgeProperty = net.minecraftforge.common.config.Property


  final val DEFAULT_CONFIG_ID = net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL

}
