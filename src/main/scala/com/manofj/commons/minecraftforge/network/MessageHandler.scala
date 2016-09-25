package com.manofj.commons.minecraftforge.network

import scala.reflect.ClassTag

import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler


/** [[com.manofj.commons.minecraftforge.network.SimpleNetworkMod]] に登録可能なメッセージのハンドラ
  *
  * @tparam A 受信するメッセージ
  * @tparam B 返信するメッセージ
  */
trait MessageHandler[ A <: IMessage, B <: IMessage ]
  extends IMessageHandler[ A, B ]
{

  /** 受信するメッセージのクラスを返す
    */
  def requestMessageClass( implicit tag: ClassTag[ A ] ): Class[ A ] = tag.runtimeClass.asInstanceOf[ Class[ A ] ]

}
