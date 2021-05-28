package com.msomu.squareissues.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msomu.squareissues.data.IssuesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * The [HomeViewModel] takes in a [IssuesRepository] to request data, transforms the data into a
 * HomeViewState that can be exposed by the [viewState] flow in order for the view to render
 * the relevant issues
 */
class HomeViewModel(
    private val issuesRepository: IssuesRepository
) : ViewModel(){

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState : StateFlow<HomeViewState> = _viewState

    init {
        fetchIssues()
    }

    private fun fetchIssues(){
        viewModelScope.launch {
            val issues = issuesRepository.getIssues()
            _viewState.value = _viewState.value.copy(
                issueLists = issues
            )
        }
    }
}