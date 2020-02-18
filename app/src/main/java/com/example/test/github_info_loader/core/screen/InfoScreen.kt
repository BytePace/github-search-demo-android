package com.example.test.github_info_loader.core.screen

import androidx.fragment.app.Fragment
import com.example.test.github_info_loader.core.fragments.GithubInfoFragment
import com.example.test.github_info_loader.core.model.SimpleRepositoryInfo
import ru.terrakok.cicerone.android.support.SupportAppScreen

class InfoScreen(private val data: SimpleRepositoryInfo) : SupportAppScreen() {

    override fun getFragment(): Fragment? {
        return GithubInfoFragment(data)
    }
}