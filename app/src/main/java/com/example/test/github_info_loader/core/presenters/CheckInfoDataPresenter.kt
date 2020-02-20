package com.example.test.github_info_loader.core.presenters

import com.example.test.github_info_loader.core.RootApplication
import com.example.test.github_info_loader.core.model.SimpleRepositoryInfo
import com.example.test.github_info_loader.core.screen.InfoScreen
import com.example.test.github_info_loader.core.view.CheckInfoDataView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CheckInfoDataPresenter : MvpPresenter<CheckInfoDataView>() {

    @Inject
    lateinit var router: Router

    fun toDetailInfo(smp: SimpleRepositoryInfo) {
        router.navigateTo(InfoScreen(smp))
    }
}