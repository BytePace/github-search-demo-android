package com.example.test.github_info_loader.core.presenters

import com.example.test.github_info_loader.core.view.ToGeneralFragmentView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ToGeneralFragmentPresenter : MvpPresenter<ToGeneralFragmentView>() {
    @Inject
    lateinit var router: Router

    fun toGeneralFragment() {
        router.exit()
    }
}