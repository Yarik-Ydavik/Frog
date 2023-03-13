package com.example.frog.data

import com.example.frog.network.FrogApi

interface FrogRepositories{
    suspend fun getFrog():MutableList<FrogInfoItem>
}
class defaultFrogRepositories : FrogRepositories{
    override suspend fun getFrog():MutableList<FrogInfoItem>{
        return FrogApi.retrofitApiService.getFrog()
    }
}