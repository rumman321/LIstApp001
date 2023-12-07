package com.app.list_app

import org.json.JSONArray
import org.json.JSONObject

object TaskListConverter {
    fun taskListToString(taskList: List<Task>): String {
        val jsonArray = JSONArray()
        for (task in taskList) {
            val jsonObject = JSONObject()
            jsonObject.put("title", task.title)
            jsonObject.put("isCompleted", task.isCompleted)
            jsonArray.put(jsonObject)
        }
        return jsonArray.toString()
    }

    fun stringToTaskList(taskListString: String): List<Task> {
        val taskList = mutableListOf<Task>()
        val jsonArray = JSONArray(taskListString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val title = jsonObject.getString("title")
            val isCompleted = jsonObject.getBoolean("isCompleted")
            val task = Task(title, isCompleted)
            taskList.add(task)
        }
        return taskList
    }
}