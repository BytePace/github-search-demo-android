package com.example.test.github_info_loader.core.presenters

import com.example.test.github_info_loader.DEFAULT_PAGE_SIZE
import com.example.test.github_info_loader.core.model.RepositoryInfoList
import com.example.test.github_info_loader.core.view.ListInfoView
import com.example.test.github_info_loader.web.RepositoryInfoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ListInfoPresenter : MvpPresenter<ListInfoView>() {

    @Inject
    lateinit var mRepositoryService: RepositoryInfoApi
    private lateinit var repositoryInfoList: RepositoryInfoList
    private lateinit var bufferKeyWord: String
    private var page = 0

    fun pageLoad() {
        GlobalScope.launch {
            mRepositoryService.repositoryList(bufferKeyWord, page++, DEFAULT_PAGE_SIZE).execute()
                .body()?.also {
                repositoryInfoList = it
                withContext(context = Dispatchers.Main) {
                    viewState.drawInfo(repositoryInfoList.list)
                }
            }
        }
    }

    fun infoLoad(s: String) {
        if (s.isNotEmpty()) {
            page = 0
            bufferKeyWord = s
            GlobalScope.launch {
                if(bufferKeyWord.isNotEmpty()) {
                    mRepositoryService.repositoryList(s, page++, DEFAULT_PAGE_SIZE).execute().body()
                        ?.also {
                            repositoryInfoList = it
                            withContext(context = Dispatchers.Main) {
                                viewState.clearInfo()
                                viewState.drawInfo(repositoryInfoList.list)
                            }
                        }
                } else {
                    viewState.clearInfo()
                }
            }
        }
    }
}