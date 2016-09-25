package com.manofj.commons.minecraftforge.base


/** MinecraftForgeを使用するMod
  * [ Mod開発言語 ]の定数と
  * [ ID, 名前, バージョン ]のフィールドを持つ
  */
trait MinecraftForgeMod {
  final val modLanguage = "scala"

  val modId: String
  val modName: String
  val modVersion: String
}
