package com.example.test.github_info_loader.core.fragments

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.test.github_info_loader.R
import com.example.test.github_info_loader.core.instance.RouterRootApplication
import com.example.test.github_info_loader.core.model.SimpleRepositoryInfo
import com.example.test.github_info_loader.core.view.DetailedInformationView
import kotlinx.android.synthetic.main.detailed_info.*
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class GithubInfoFragment(private val data: SimpleRepositoryInfo) : MvpAppCompatFragment(R.layout.detailed_info) {
    private lateinit var backButton: Button
    private lateinit var userName: TextView
    private lateinit var repositoryName: TextView
    private lateinit var lastCommit: TextView
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = SupportAppNavigator(this.activity!!, -1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton = button
        userName = userNameId
        repositoryName = repositoryNameId
        lastCommit = lastCommitId
        backButton.setOnClickListener {
            RouterRootApplication.instance?.getRouter()?.exit()
        }
        userName.text = data.repositoryOwner.userName
        repositoryName.text = data.repositoryName
        lastCommit.text = "kekv"
    }
}