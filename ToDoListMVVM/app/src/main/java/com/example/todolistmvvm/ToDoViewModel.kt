package com.example.todolistmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistmvvm.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel : ViewModel() {
    private val url = "https://jsonplaceholder.typicode.com/todos"
    private var mutableData :MutableLiveData<ArrayList<Model>?> = MutableLiveData()

    fun init() {
        viewModelScope.launch() {
            //val data = ToDoRepository.initList(url)
            val data = ToDoRepository.getData()
            mutableData.postValue(data)
            
        }
    }

    fun getListArray(): LiveData<ArrayList<Model>?> {
        return mutableData
    }
}