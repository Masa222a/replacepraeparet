package com.hannibal.replacepraeparet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hannibal.replacepraeparet.model.XmlManager

class DetailVisaFragmentViewModel : ViewModel() {
    var visaData = MutableLiveData<String>()
    private val xmlManager = XmlManager()

    fun getVisaData(id: Int) {
        val data = xmlManager.changeVisaList(id)
        visaData.postValue(data[0].content.replace(" +".toRegex(), "\n"))
    }
}
