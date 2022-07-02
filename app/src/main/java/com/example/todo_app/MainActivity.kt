// Author : Nemuel Wainaina

package com.example.todo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var todoList = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, todoList)
        var todo = findViewById<EditText>(R.id.editText)
        var add = findViewById<Button>(R.id.add)
        var delete = findViewById<Button>(R.id.delete)
        var clear = findViewById<Button>(R.id.clear)
        var listView = findViewById<ListView>(R.id.listView)

        // add a new Todo Item
        add.setOnClickListener() {
            todoList.add(todo.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            todo.text.clear()
        }

        // delete the selected Todo items
        delete.setOnClickListener() {
            var positions: SparseBooleanArray = listView.checkedItemPositions
            var count: Int = listView.count
            var todoItem = count - 1
            while(todoItem >= 0) {
                if(positions.get(todoItem)) {
                    adapter.remove(todoList.get(todoItem))
                }
                todoItem --
            }
            positions.clear()
            adapter.notifyDataSetChanged()
        }

        // clear the entire list of Todo items
        clear.setOnClickListener() {
            todoList.clear()
            adapter.notifyDataSetChanged()
        }
    }
}