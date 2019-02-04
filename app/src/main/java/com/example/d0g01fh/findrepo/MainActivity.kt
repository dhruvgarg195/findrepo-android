package com.example.d0g01fh.findrepo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            var searchButton = findViewById<Button>(R.id.searchButton)
            var searchEditText = findViewById<EditText>(R.id.searchEditText)

            searchButton.setOnClickListener {
                var toSearchActivityIntent = Intent(this,SearchResultActivity::class.java)
                toSearchActivityIntent.putExtra("searchItem",searchEditText.text.toString())
                startActivity(toSearchActivityIntent)
            }
        }

    }

