package com.sampson.kotlintodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvTotoItems = findViewById<RecyclerView>(R.id.rvTodoItems)
        val tvTitle = findViewById<EditText>(R.id.etTodoItem)
        val btnAdd = findViewById<Button>(R.id.btnInsertItem)
        val btnRemove = findViewById<Button>(R.id.btnRemoveItem)

        todoAdapter = TodoAdapter(mutableListOf())
        rvTotoItems.adapter = todoAdapter
        rvTotoItems.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val todoTitle = tvTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                tvTitle.text.clear()
            }
        }

        btnRemove.setOnClickListener {
            todoAdapter.deleteDoneTodo()
        }
    }
}