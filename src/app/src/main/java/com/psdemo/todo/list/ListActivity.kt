package com.psdemo.todo.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.psdemo.todo.add.AddActivity
import com.psdemo.todo.databinding.ActivityMainBinding
import com.psdemo.todo.obtainViewModel

class ListActivity : AppCompatActivity(), TodoAdapter.OnClickListener {
    override fun onCheckboxChecked(id: String) {
        listViewModel.toggleTodo(id)
    }

    private lateinit var  binding : ActivityMainBinding
    private lateinit var listViewModel: ListViewModel
    private var adapter = TodoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        listViewModel = obtainViewModel(ListViewModel::class.java)

        binding.contentMain.listTodos.layoutManager = LinearLayoutManager(this)
        binding.contentMain.listTodos.adapter = adapter
        listViewModel.allTodos.observe(this, Observer { todos ->
            todos?.let {
                adapter.setTodos(todos)
            }
        })

        listViewModel.upcomingTodosCount.observe(this, Observer { count ->
            binding.contentMain.soonValue.text = count.toString()
        })
    }
}
