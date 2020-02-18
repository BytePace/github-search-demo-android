package com.example.test.github_info_loader.core.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.github_info_loader.R
import com.example.test.github_info_loader.core.adapter.InfoRecyclerViewAdapter
import com.example.test.github_info_loader.core.instance.RouterRootApplication
import com.example.test.github_info_loader.core.model.SimpleRepositoryInfo
import com.example.test.github_info_loader.core.presenters.ListInfoPresenter
import com.example.test.github_info_loader.core.presenters.ToDetailInfoPresenter
import com.example.test.github_info_loader.core.screen.InfoScreen
import com.example.test.github_info_loader.core.util.TimeLockTextWatcher
import com.example.test.github_info_loader.core.view.CheckInfoDataView
import com.example.test.github_info_loader.core.view.DetailedInformationView
import com.example.test.github_info_loader.core.view.ListInfoView
import kotlinx.android.synthetic.main.search_info_layout.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class SearchInfoFragment : MvpAppCompatFragment(R.layout.search_info_layout), CheckInfoDataView, ListInfoView {
    private lateinit var mInputField: EditText

    @InjectPresenter
    lateinit var listInfoPresenter: ListInfoPresenter

    @InjectPresenter
    lateinit var checkInfoPresenter: ToDetailInfoPresenter

    private lateinit var infoRecyclerView: RecyclerView
    private lateinit var infoRecyclerViewAdapter: InfoRecyclerViewAdapter
    private lateinit var navigator: SupportAppNavigator

    override fun toDetailInfo(smp: SimpleRepositoryInfo) {
        RouterRootApplication.instance?.getRouter()?.navigateTo(InfoScreen(smp))
    }

    override fun drawInfo(toDraw: List<SimpleRepositoryInfo>) {
        infoRecyclerView.post {
            infoRecyclerViewAdapter.updateInfo(toDraw)
        }
    }

    override fun clearInfo() {
        infoRecyclerViewAdapter.clearData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        infoRecyclerView = info_recycler_view
        infoRecyclerViewAdapter =
            InfoRecyclerViewAdapter(listInfoPresenter::pageLoad, this::toDetailInfo)
        infoRecyclerView.adapter = infoRecyclerViewAdapter
        infoRecyclerView.layoutManager = LinearLayoutManager(this.activity)
        mInputField = inputKeyWords
        mInputField.addTextChangedListener(TimeLockTextWatcher(listInfoPresenter::infoLoad))
        super.onViewCreated(view, savedInstanceState)
        navigator = SupportAppNavigator(this.activity!!, -1)
    }
}