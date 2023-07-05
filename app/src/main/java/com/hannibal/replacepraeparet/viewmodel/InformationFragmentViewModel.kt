package com.hannibal.replacepraeparet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hannibal.replacepraeparet.model.Flag
import com.hannibal.replacepraeparet.model.XmlManager

class InformationFragmentViewModel : ViewModel() {
    var flagList = MutableLiveData(listOf<Flag>())
    private val xmlManager = XmlManager()
    var tabPosition = MutableLiveData<Int>()

    fun getFlagList(region: XmlManager.Region) {
        flagList.postValue(xmlManager.changeCountriesList(region))
    }
}
