package com.example.d0g01fh.findrepo

import android.telecom.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService{
    @GET("search/repositories?")
    fun searchRepos(@Query("q") searchTerm :String) : retrofit2.Call<GitHubSearchResult>
}

class GitHubSearchResult(val items: List<Repo>)
class Repo(val full_name:String,val owner : GitHubUser, val html_url: String)
class GitHubUser(val avatar_url: String)

class GitHubRetriever{
    val service : GitHubService

    init{
        val retroFit =  Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retroFit.create(GitHubService::class.java)
    }

    fun searchRepos(callback: Callback<GitHubSearchResult>, searchTerm: String){
        var searchT = searchTerm
        if(searchT == "")
        {
            searchT = "kotlin"
        }
        val call = service.searchRepos(searchT)
        call.enqueue(callback)
    }
}
