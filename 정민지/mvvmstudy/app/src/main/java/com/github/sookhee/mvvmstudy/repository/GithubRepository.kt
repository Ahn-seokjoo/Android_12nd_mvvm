package com.github.sookhee.mvvmstudy.repository

import android.util.Log
import com.github.sookhee.mvvmstudy.ResultState
import com.github.sookhee.mvvmstudy.network.mapper.ResponseMapper
import com.github.sookhee.mvvmstudy.model.GithubRepositoryModel
import com.github.sookhee.mvvmstudy.network.GithubAPI
import javax.inject.Inject

/**
 *  GithubRepository.kt
 *
 *  Created by Minji Jeong on 2022/04/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

class GithubRepository @Inject constructor(private val githubApi: GithubAPI) {
    suspend fun getGithubRepositoryList(): ResultState<List<GithubRepositoryModel>> {
        return try {
            val response = githubApi.getRepository()
            Log.d(TAG, "onResponse: $response")

            ResultState.Success(ResponseMapper.mapToGithubRepositoryModelList(response))
        } catch (e: Exception) {
            Log.d(TAG, "onFailure: ${e.message}}")

            ResultState.Error("${e.message}")
        }
    }

    suspend fun getGithubRepositoryListWithQuery(keyword: String): ResultState<List<GithubRepositoryModel>> {
        return try {
            val response = githubApi.getRepositoryListWithQuery(keyword)
            Log.d(TAG, "onResponse: $response")

            ResultState.Success(ResponseMapper.mapToGithubRepositoryModelList(response.items))
        } catch (e: Exception) {
            Log.d(TAG, "onFailure: ${e.message}}")

            ResultState.Error("${e.message}")
        }
    }

    companion object {
        private val TAG = GithubRepository::class.simpleName
    }
}
