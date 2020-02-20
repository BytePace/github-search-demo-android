package com.example.test.github_info_loader.core.activity

import android.os.Bundle
import com.example.test.github_info_loader.R
import com.example.test.github_info_loader.core.RootApplication
import com.example.test.github_info_loader.core.screen.SearchScreen
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(R.layout.list_info_activity) {
    @Inject
    lateinit var router: Router

    lateinit var navigator: SupportAppNavigator

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RootApplication.appComponent.inject(this)
        navigator = SupportAppNavigator(this, R.id.fragment_container)
        navigatorHolder.setNavigator(navigator)
        router.navigateTo(SearchScreen())
    }
}
