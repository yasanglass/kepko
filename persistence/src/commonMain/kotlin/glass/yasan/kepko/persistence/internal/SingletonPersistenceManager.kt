package glass.yasan.kepko.persistence.internal

import com.russhwolf.settings.Settings
import glass.yasan.kepko.persistence.PersistenceManager

internal object SingletonPersistenceManager {

    val instance: PersistenceManager by lazy {
        PersistenceManagerImpl(
            settings = Settings(),
        )
    }

}
