package com.manofj.commons.minecraftforge.resource

import net.minecraft.util.ResourceLocation


/** リソースロケーションを生成するメソッドを持つ
  */
trait ResourceLocationMaker {

  def resourceDomain: String


  def resourceLocation( path: String ): ResourceLocation = new ResourceLocation( resourceDomain, path )

}
