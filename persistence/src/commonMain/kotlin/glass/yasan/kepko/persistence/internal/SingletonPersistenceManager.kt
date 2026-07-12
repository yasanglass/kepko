package glass.yasan.kepko.persistence.internal

import com.russhwolf.settings.Settings
import glass.yasan.kepko.foundation.annotation.InternalKepkoApi
import glass.yasan.kepko.persistence.PersistenceManager

@InternalKepkoApi
public object SingletonPersistenceManager {

    public val instance: PersistenceManager by lazy {
        PersistenceManagerImpl(
            settings = Settings(),
        )
    }
}
