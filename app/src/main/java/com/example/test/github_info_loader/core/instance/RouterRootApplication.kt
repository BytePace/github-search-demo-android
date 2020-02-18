package com.example.test.github_info_loader.core.instance

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class RouterRootApplication : Application() {

    companion object {
        var instance: RouterRootApplication? = null
    }
    private lateinit var navigator: Cicerone<Router>
    override fun onCreate() {
        super.onCreate()
        instance = this
        navigator = Cicerone.create()
    }


    fun getNavigationHolder(): NavigatorHolder {
        return try {
            navigator.navigatorHolder
        } catch (e: UninitializedPropertyAccessException) {
            throw UninitializedPropertyAccessException("Holder is not initialized")
        }
    }
    fun getRouter(): Router {
        return try {
            navigator.router
        } catch (e: UninitializedPropertyAccessException) {
            throw UninitializedPropertyAccessException("Router is not initialized")
        }
    }
}