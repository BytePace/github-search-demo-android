package com.example.test.github_info_loader.core.screen

import androidx.fragment.app.Fragment
import com.example.test.github_info_loader.core.fragments.SearchInfoFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SearchScreen : SupportAppScreen() {

    override fun getFragment(): Fragment? {
        return SearchInfoFragment()
    }
}