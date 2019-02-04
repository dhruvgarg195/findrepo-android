 package com.example.d0g01fh.findrepo

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class SearchResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        var searchText = intent.getStringExtra("searchItem")

        val callback = object : Callback<GitHubSearchResult>{
            override fun onFailure(call: Call<GitHubSearchResult>, t: Throwable) {
                println("not working")
            }

            override fun onResponse(call: Call<GitHubSearchResult>, response: Response<GitHubSearchResult>) {

                val searchResult = response?.body()
                if(searchResult != null){
                    for(repo in searchResult!!.items)
                    {
                        println(repo.html_url)
                    }

                    var listView = findViewById<ListView>(R.id.repoListView)

                    listView.setOnItemClickListener { parent, view, position, id ->
                        val selectedrepo = searchResult!!.items[position]

                        // click the URL to open the link in browser
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedrepo.html_url))
                        startActivity(intent)
                    }

                    var adapter= RepoAdapter(this@SearchResultActivity,android.R.layout.simple_list_item_1,searchResult!!.items)
                    listView.adapter = adapter
                }
            }

        }

        val retriever = GitHubRetriever()
        retriever.searchRepos(callback,searchText)
    }
}

 class RepoAdapter(context: Context, resource: Int, objects: List<Repo>) :
     ArrayAdapter<Repo>(context, resource, objects) {

     override fun getCount(): Int {
         return super.getCount()
     }

     override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
         val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
         val repoView = inflator.inflate(android.R.layout.simple_list_item_1,parent,false ) as TextView

         var repoItem = getItem(position)
         repoView.text = repoItem.full_name

         return repoView
     }

 }
