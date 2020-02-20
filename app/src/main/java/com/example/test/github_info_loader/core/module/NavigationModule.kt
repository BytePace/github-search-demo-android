package com.example.test.github_info_loader.core.module

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideCicerone() : Cicerone<Router> {
        return Cicerone.create()
    }

    @Provides
    @Singleton
    fun provideCiceroneRouter(cicerone: Cicerone<Router>) : Router {
        return cicerone.router
    }

    @Provides
    @Singleton
    fun provideCiceroneHolder(cicerone: Cicerone<Router>) : NavigatorHolder {
        return cicerone.navigatorHolder
    }
}