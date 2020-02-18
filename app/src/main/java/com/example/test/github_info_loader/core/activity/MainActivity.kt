package com.example.test.github_info_loader.core.activity

import android.os.Bundle
import com.example.test.github_info_loader.R
import com.example.test.github_info_loader.core.instance.RouterRootApplication
import com.example.test.github_info_loader.core.screen.SearchScreen
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(R.layout.list_info_activity) {

    private lateinit var navigator: SupportAppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = SupportAppNavigator(this, R.id.fragment_container)
        RouterRootApplication.instance?.getNavigationHolder()?.setNavigator(navigator)
        RouterRootApplication.instance?.getRouter()?.navigateTo(SearchScreen())
    }
}
