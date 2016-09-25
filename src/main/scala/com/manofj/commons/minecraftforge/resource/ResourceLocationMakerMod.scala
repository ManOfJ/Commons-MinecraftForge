package com.manofj.commons.minecraftforge.resource

import com.manofj.commons.minecraftforge.base.MinecraftForgeMod


/** 独自のリソースを持つMod
  */
trait ResourceLocationMakerMod
  extends ResourceLocationMaker
{
  this: MinecraftForgeMod =>


  override val resourceDomain: String = this.modId

}
