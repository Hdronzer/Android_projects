package com.example.todolistmvvm.repository


import com.example.todolistmvvm.Model
import com.example.todolistmvvm.ToDoListAPIService
import okhttp3.*
import org.json.JSONArray

object ToDoRepository: BaseRepository() {
    private val client = OkHttpClient()
    private lateinit var toDoList:ArrayList<Model>

    suspend fun getData () : ArrayList<Model>? {

        val jsonResponse = safeApiCall(
            call = {ToDoListAPIService.api.getListDataAsync().await()},
            errorMessage = "Error Fetching JSON Data"
        )

        return jsonResponse as ArrayList<Model>?
    }

    fun initList(url:String) : ArrayList<Model>? {
        val request = Request.Builder()
            .url(url)
            .build()
         try {
             client.newCall(request).execute().use { response ->

                     val respString = response.body!!.string()
                     //creating json array
                     val jsonArray =  JSONArray(respString)

                     val size: Int = jsonArray.length()
                     toDoList = ArrayList()
                     for (i in 0 until size) {
                         val objectDetail = jsonArray.getJSONObject(i)
                         val status = objectDetail.getString("completed")
                         Model(status).apply {
                             id = objectDetail.getString("id")
                             userId = objectDetail.getString("userId")
                             title = objectDetail.getString("title")
                             toDoList.add(this)
                         }
                     }
             }
         }catch (e :Exception ) {
            e.printStackTrace()
             return null
        }

        return toDoList

    }

    fun checkPositive(value:Int):Boolean {// sample fun for unit test case demo
        return value > 0
    }
}