package com.manofj.commons.minecraftforge.config.gui

import net.minecraft.client.gui.GuiScreen

import net.minecraftforge.fml.client.config.GuiConfig


/** Modのコンフィグを操作するGUI
  * コンテンツは [[com.manofj.commons.minecraftforge.config.gui.GuiParams]] にて提供される
  */
abstract class ConfigGui( parent: GuiScreen, params: GuiParams )
  extends GuiConfig( parent,
                     params.configElements,
                     params.modId,
                     params.configId,
                     params.allRequireWorldRestart,
                     params.allRequireMcRestart,
                     params.title,
                     params.titleLine2 )
