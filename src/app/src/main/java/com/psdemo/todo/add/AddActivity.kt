package com.psdemo.todo.add

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.psdemo.todo.databinding.ActivityAddBinding
import com.psdemo.todo.obtainViewModel
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var addViewModel: AddViewModel
    private lateinit var  binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addViewModel = obtainViewModel(AddViewModel::class.java)

        binding.due.date = System.currentTimeMillis()
        binding.due.setOnDateChangeListener { _, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            addViewModel.todo.dueDate = calendar.timeInMillis
            binding.clearDue.visibility = View.VISIBLE
        }

        binding.save.setOnClickListener {
            addViewModel.todo.title = binding.txtTitle.text.toString()

            val message = addViewModel.save()
            if (message != null) {
                Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
            } else {
                finish()
            }
        }

        binding.clearDue.setOnClickListener {
            addViewModel.todo.dueDate = null
            binding.clearDue.visibility = View.INVISIBLE
            binding.due.visibility = View.INVISIBLE
            binding.setDue.visibility = View.VISIBLE
        }

        binding.setDue.setOnClickListener {
            binding.clearDue.visibility = View.VISIBLE
            binding.due.visibility = View.VISIBLE
            binding.setDue.visibility = View.INVISIBLE
        }

    }
}
