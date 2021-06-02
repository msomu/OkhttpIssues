package com.msomu.squareissues.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msomu.squareissues.data.Comment
import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.data.IssueRepository
import com.msomu.squareissues.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class IssueDetailViewModel @Inject constructor(
    private val repository: IssueRepository
) : ViewModel() {
    private val _viewIssueState: MutableStateFlow<GithubIssuesItem?> = MutableStateFlow(null)
    val viewIssueState: StateFlow<GithubIssuesItem?> = _viewIssueState

    private val _viewCommentsState: MutableStateFlow<Resource<List<Comment>>> =
        MutableStateFlow(Resource.Loading(null))
    val viewCommentsState: StateFlow<Resource<List<Comment>>> = _viewCommentsState

    fun getData(issueNumber: Int) {
        getIssueId(issueNumber = issueNumber)
        getComments(issueNumber = issueNumber)
    }

    private fun getIssueId(issueNumber: Int) {
        repository.getIssue(issueNumber).onEach {
            _viewIssueState.value = it
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    private fun getComments(issueNumber: Int) {
        repository.getComments(issueNumber).onEach {
            _viewCommentsState.value = it
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }
}