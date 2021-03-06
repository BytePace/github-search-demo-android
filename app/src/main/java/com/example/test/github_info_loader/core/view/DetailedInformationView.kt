package com.example.test.github_info_loader.core.view

import com.example.test.github_info_loader.core.model.SimpleRepositoryInfo
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

interface DetailedInformationView : MvpView {
    @AddToEnd
    fun toDetailInfo(smp: SimpleRepositoryInfo)
}