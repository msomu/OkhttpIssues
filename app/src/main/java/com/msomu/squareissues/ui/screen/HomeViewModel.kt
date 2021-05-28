package com.msomu.squareissues.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.data.IssueRepository
import com.msomu.squareissues.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * The [HomeViewModel] takes in a [IssuesRepository] to request data, transforms the data into a
 * HomeViewState that can be exposed by the [viewState] flow in order for the view to render
 * the relevant issues
 */
private const val TAG = "HomeViewModel"
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IssueRepository
) : ViewModel() {

//    private val _viewState = MutableStateFlow(HomeViewState())
//    val viewState: StateFlow<HomeViewState> = _viewState

    private val _viewState : MutableStateFlow<Resource<List<GithubIssuesItem>>> = MutableStateFlow(Resource.Loading(null))
    val viewState: StateFlow<Resource<List<GithubIssuesItem>>> = _viewState

    init {
        fetchIssues()
    }

    private fun fetchIssues() {
        Log.d(TAG, "fetchIssues: started")
        viewModelScope.launch {
            _viewState.value = repository.getIssues().stateIn(this).value
        }
    }
}