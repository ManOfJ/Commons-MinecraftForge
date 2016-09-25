package com.manofj.commons.minecraftforge.i18n


/** 国際化に対応するためのメソッドを持つ
  */
trait I18nSupport {

  /** 指定された文字列から言語キーを生成する
    *
    * @param base 言語キーのベース文字列
    */
  def languageKey( base: String, prefix: String = "", suffix: String = "" ): String

  /** 指定された言語キーに対応する訳語が存在すれば `true` を
    * そうでなければ `false` を返す
    *
    * @param key 対象言語キー
    */
  def hasKey( key: String ): Boolean

  /** 指定された言語キーに対応する訳語を整形して返す
    * 対応する訳語が存在しない場合は言語キーをそのまま返す
    *
    * @param key  対象言語キー
    * @param args 整形に使用するパラメータ
    */
  def message( key: String, args: AnyRef* ): String

  /** 指定された文字列から言語キーを生成し､対応する訳語を整形して返す
    * 対応する訳語が存在しない場合は言語キーをそのまま返す
    *
    * @param base 言語キーのベース文字列
    * @param args 整形に使用するパラメータ
    */
  def format( base: String, args: AnyRef* ): String = message( languageKey( base ), args: _* )

}
