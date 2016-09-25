package com.manofj.commons.minecraftforge.config.gui

import scala.reflect.ClassTag

import net.minecraft.client.Minecraft

import net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionCategoryElement
import net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionGuiHandler

import com.manofj.commons.scala.alias.java.Collection.JavaSet


/** シンプルな実装の [[com.manofj.commons.minecraftforge.config.gui.GuiFactory]]
  */
abstract class SimpleGuiFactory[ A <: ConfigGui : ClassTag ]
  extends GuiFactory[ A ]
{

  override def mainConfigGuiClass(): Class[ A ] = implicitly[ ClassTag[ A ] ].runtimeClass.asInstanceOf[ Class[ A ] ]

  override def getHandlerFor( element: RuntimeOptionCategoryElement ): RuntimeOptionGuiHandler = null

  override def initialize( minecraftInstance: Minecraft ): Unit = {}

  override def runtimeGuiCategories(): JavaSet[ RuntimeOptionCategoryElement ] = null

}
