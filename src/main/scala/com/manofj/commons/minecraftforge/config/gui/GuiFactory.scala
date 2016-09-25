package com.manofj.commons.minecraftforge.config.gui

import net.minecraftforge.fml.client.IModGuiFactory


/** コンフィグGUIのファクトリ
  */
trait GuiFactory[ A <: ConfigGui ]
  extends IModGuiFactory
{
  override def mainConfigGuiClass(): Class[ A ]
}
