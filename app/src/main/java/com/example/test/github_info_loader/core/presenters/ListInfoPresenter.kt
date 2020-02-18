package com.example.test.github_info_loader.core.presenters

import com.example.test.github_info_loader.DEFAULT_PAGE_SIZE
import com.example.test.github_info_loader.core.model.RepositoryInfoList
import com.example.test.github_info_loader.core.view.ListInfoView
import com.example.test.github_info_loader.web.GHInfoLoader
import com.example.test.github_info_loader.web.RepositoryInfoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class ListInfoPresenter : MvpPresenter<ListInfoView>() {
    val router: Router = Router()
    private val mInfoLoader: GHInfoLoader = GHInfoLoader()
    private val mRepositoryService: RepositoryInfoApi =
        mInfoLoader.retrofit.create(RepositoryInfoApi::class.java)
    private lateinit var repositoryInfoList: RepositoryInfoList
    private lateinit var bufferKeyWord: String
    private var page = 0

    fun pageLoad(pageNum: Int) {
        GlobalScope.launch {
            repositoryInfoList = mRepositoryService.repositoryList(bufferKeyWord, ++page, DEFAULT_PAGE_SIZE).execute().body()!!
            withContext(context = Dispatchers.Main) {
                viewState.drawInfo(repositoryInfoList.list)
            }
        }
    }

    fun infoLoad(s: String) {
        page = 0
        bufferKeyWord = s
        GlobalScope.launch {
            withContext(context = Dispatchers.Main) {
                viewState.clearInfo()
            }
            if(mRepositoryService.repositoryList(s, ++page, DEFAULT_PAGE_SIZE).execute().isSuccessful) {
                repositoryInfoList = mRepositoryService.repositoryList(s, ++page, DEFAULT_PAGE_SIZE).execute().body()!!
            }
            withContext(context = Dispatchers.Main) {
                viewState.drawInfo(repositoryInfoList.list)
            }
        }
    }
}