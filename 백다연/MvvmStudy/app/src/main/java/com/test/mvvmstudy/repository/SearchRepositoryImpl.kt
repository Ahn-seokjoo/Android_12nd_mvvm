package com.test.mvvmstudy.repository

import com.test.mvvmstudy.api.Retrofit
import com.test.mvvmstudy.data.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl : SearchRepository {
    override fun getSearchItem(query: String): Flow<SearchResult> = flow {
        emit(Retrofit.githubApi.getSearchList(query))
    }
}