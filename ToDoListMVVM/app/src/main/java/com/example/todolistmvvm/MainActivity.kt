package com.example.todolistmvvm

import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    lateinit var toDoAdapter: ToDoListAdapter
    lateinit var viewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        offlinePage.visibility = LinearLayout.GONE
        recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }

        viewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        run()
        viewModel.getListArray().observe(this, Observer {

            it?.let {
                if(!::toDoAdapter.isInitialized) {
                    offlinePage.visibility = LinearLayout.GONE
                    listLayout.visibility = LinearLayout.VISIBLE
                    toDoAdapter = ToDoListAdapter(this, it)
                    recycleView.adapter = toDoAdapter
                }else
                    toDoAdapter.addItems(it)
                return@Observer
            }
            listLayout.visibility = LinearLayout.GONE
            offlinePage.visibility = LinearLayout.VISIBLE
        })
        reloadButton.setOnClickListener {
            run()
        }
    }

    private fun run() {
        viewModel.init()
    }
}