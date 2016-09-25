package com.manofj.commons.minecraftforge.config.gui

import net.minecraftforge.common.config.ConfigElement
import net.minecraftforge.fml.client.config.IConfigElement

import com.manofj.commons.scala.alias.java.Collection.JavaList

import com.manofj.commons.minecraftforge.config.DEFAULT_CONFIG_ID
import com.manofj.commons.minecraftforge.config.ForgeConfig


/** シンプルな実装の [[com.manofj.commons.minecraftforge.config.gui.GuiParams]]
  */
trait SimpleGuiParams
  extends GuiParams
{

  /** GUIが取り扱うコンフィグ
    */
  protected def config: ForgeConfig


  override def configElements: JavaList[ IConfigElement ] =
    new ConfigElement( config.getCategory( configId ) ).getChildElements

  override def configId: String = DEFAULT_CONFIG_ID

  override def allRequireWorldRestart: Boolean = false

  override def allRequireMcRestart: Boolean = false

  override def titleLine2: String = null

}
