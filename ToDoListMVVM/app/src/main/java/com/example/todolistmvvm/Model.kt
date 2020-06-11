package com.example.todolistmvvm

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

data class Model( var completed : String ) {
    lateinit var userId: String
    lateinit var id: String
    lateinit var title: String

    constructor(userId: String, id: String, title: String, completed: String) : this(completed) {
        this.userId = userId
        this.id = id
        this.title = title
        //this.status = status
    }
}

    //A retrofit Network Interface for the Api
    interface ToDoAPI{
        @GET("todos")
        fun getListDataAsync(): Deferred<Response<List<Model>>>
    }


