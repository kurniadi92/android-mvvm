package com.rplits.viper

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.rplits.mvvm.R

class MainViewModel constructor(
    private val repository: UniversityRepository,
)  : ViewModel() {

    val universityListUpdate = MutableLiveData<List<University>>()
    val openDetail = MutableLiveData<University>()
    var showToast = MutableLiveData<String>()
    private var items = mutableListOf<University>()
    fun onCreate() {
        items = repository.fetchUniversity().sortedWith(compareBy { it.name }).toMutableList()
        universityListUpdate.postValue(items.toList())
    }

    fun onLongTap(index: Int) {
        showToast.postValue("${items[index]} removed")
        items.removeAt(index)
        universityListUpdate.postValue(items.toList())
    }

    fun onTap(index: Int) {
        openDetail.postValue(items[index])
    }
}

class UniversityRepository(private val context: Context) {
    fun fetchUniversity(): List<University> {
        val objectArrayString: String = context.resources.openRawResource(R.raw.university_list).bufferedReader().use { it.readText() }
        return Gson().fromJson(objectArrayString, UniversityList::class.java).list
    }
}