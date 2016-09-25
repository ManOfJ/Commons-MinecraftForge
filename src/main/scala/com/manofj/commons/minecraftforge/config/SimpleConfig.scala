package com.manofj.commons.minecraftforge.config


/** シンプルな実装の [[com.manofj.commons.minecraftforge.config.Config]]
  * コンフィグの設定項目へのアクセス手段は子クラスで実装する
  */
trait SimpleConfig
  extends Config
{

  private[ this ] var configOpt = Option.empty[ ForgeConfig ]


  override protected def configId: String = DEFAULT_CONFIG_ID

  override protected def configVersion: String = null


  override def theConfig: ForgeConfig =
    configOpt.getOrElse( throw new IllegalStateException( "Has not been captured configuration" ) )


  override def capture( config: ForgeConfig ): Unit =
    Option( config ) match {
      case None =>
        throw new NullPointerException( "Configuration is null" )

      case some @ Some( _ ) =>
        configOpt = some

        load()
        sync()
        save()
    }

  override def fix( definedVersion: String, loadedVersion: String ): Unit = {}

}
