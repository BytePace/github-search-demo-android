package com.example.test.github_info_loader.core

import com.example.test.github_info_loader.core.activity.MainActivity
import com.example.test.github_info_loader.core.fragments.GithubInfoFragment
import com.example.test.github_info_loader.core.fragments.SearchInfoFragment
import com.example.test.github_info_loader.core.module.NavigationModule
import com.example.test.github_info_loader.core.presenters.CheckInfoDataPresenter
import com.example.test.github_info_loader.core.presenters.ListInfoPresenter
import com.example.test.github_info_loader.core.presenters.ToGeneralFragmentPresenter
import com.example.test.github_info_loader.web.module.WebModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WebModule::class, NavigationModule::class])
interface AppComponent {
    fun inject(listInfoPresenter: ListInfoPresenter)
    fun inject(checkInfoDataPresenter: CheckInfoDataPresenter)
    fun inject(toGeneralFragmentPresenter: ToGeneralFragmentPresenter)
    fun inject(mainActivity: MainActivity)
}