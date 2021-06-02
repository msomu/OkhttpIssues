package com.msomu.squareissues.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * The [HomeViewModel] takes in a [IssueRepository] to request data, transforms the data into a
 * HomeViewState that can be exposed by the [viewState] flow in order for the view to render
 * the relevant issues
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IssueRepository
) : ViewModel() {

    private val _viewState : MutableStateFlow<Resource<List<GithubIssuesItem>>> = MutableStateFlow(Resource.Loading(null))
    val viewState: StateFlow<Resource<List<GithubIssuesItem>>> = _viewState

    init {
        fetchIssues()
    }

    private fun fetchIssues() {
            repository.getIssues().onEach {
                _viewState.value = it
            }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }
}