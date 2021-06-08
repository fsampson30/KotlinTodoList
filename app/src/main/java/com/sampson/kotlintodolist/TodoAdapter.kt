package com.sampson.kotlintodolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        val cbIsChecked = holder.itemView.findViewById<CheckBox>(R.id.cbItemDone)
        val tvTitle = holder.itemView.findViewById<TextView>(R.id.tvItemTodo)
        tvTitle.text = currentTodo.title
        cbIsChecked.isChecked = currentTodo.isChecked
        markTitle(tvTitle, currentTodo.isChecked)
        cbIsChecked.setOnCheckedChangeListener { _, isChecked ->
            markTitle(tvTitle, isChecked)
            currentTodo.isChecked = !currentTodo.isChecked
        }
    }

    override fun getItemCount() = todos.size

    private fun markTitle(tvTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTitle.paintFlags = tvTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTitle.paintFlags = tvTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneTodo(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }
}