package com.app.list_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    interface OnEditClickListener {
        fun onEditClick(position: Int)
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(position: Int)
    }

    interface OnCheckedChangeListener {
        fun onCheckedChange(position: Int, isChecked: Boolean)
    }

    private var editClickListener: OnEditClickListener? = null
    private var deleteClickListener: OnDeleteClickListener? = null
    private var checkedChangeListener: OnCheckedChangeListener? = null

    fun setOnEditClickListener(listener: OnEditClickListener) {
        editClickListener = listener
    }

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        deleteClickListener = listener
    }
    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener) {
        checkedChangeListener = listener
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val deletbtn:ImageButton=itemView.findViewById(R.id.imgbtn2)
        val addbutton:ImageButton=itemView.findViewById(R.id.imgbtn1)
        val titleTextView:TextView=itemView.findViewById(R.id.text1)
        val checkbox:CheckBox=itemView.findViewById(R.id.check)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {
     val task=tasks[position]
        holder.titleTextView.text=task.title
        holder.checkbox.isChecked=task.isCompleted
        holder.addbutton.setOnClickListener{
            editClickListener?.onEditClick(position)
        }
        holder.deletbtn.setOnClickListener{
            deleteClickListener?.onDeleteClick(position)
        }
        holder.checkbox.setOnCheckedChangeListener{ _,isChecked ->
            checkedChangeListener?.onCheckedChange(position,isChecked)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

}