package com.example.test.github_info_loader.core.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.test.github_info_loader.R
import com.example.test.github_info_loader.core.RootApplication
import com.example.test.github_info_loader.core.model.SimpleRepositoryInfo
import com.example.test.github_info_loader.core.presenters.ToGeneralFragmentPresenter
import com.example.test.github_info_loader.core.view.ToGeneralFragmentView
import kotlinx.android.synthetic.main.detailed_info.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class GithubInfoFragment(private val data: SimpleRepositoryInfo) :
    MvpAppCompatFragment(R.layout.detailed_info),
    ToGeneralFragmentView {

    @Inject
    @InjectPresenter
    lateinit var toGeneralFragmentPresenter: ToGeneralFragmentPresenter

    private lateinit var backButton: Button
    private lateinit var userName: TextView
    private lateinit var repositoryName: TextView
    private lateinit var lastCommit: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        initButton()
        initDataFields(data)
    }

    private fun initFields() {
        backButton = button
        userName = userNameId
        repositoryName = repositoryNameId
        lastCommit = lastCommitId
    }

    private fun initDataFields(data: SimpleRepositoryInfo) {
        userName.text = data.repositoryOwner.userName
        repositoryName.text = data.repositoryName
        lastCommit.text = data.date
    }

    private fun initButton() {
        backButton.setOnClickListener {
            toGeneralFragmentPresenter.toGeneralFragment()
        }
    }

    @ProvidePresenter
    fun provideToGeneralFragmentPresenter(): ToGeneralFragmentPresenter {
        val toGeneralFragmentPresenter = ToGeneralFragmentPresenter()
        RootApplication.appComponent.inject(toGeneralFragmentPresenter)
        return toGeneralFragmentPresenter
    }
}