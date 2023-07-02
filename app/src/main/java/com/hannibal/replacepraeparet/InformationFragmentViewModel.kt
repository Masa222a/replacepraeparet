package com.hannibal.replacepraeparet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InformationFragmentViewModel : ViewModel() {
    var flagList = MutableLiveData(listOf<Flag>())
    private val xmlManager = XmlManager()
    var tabPosition = MutableLiveData<Int>()

    fun getFlagList(region: XmlManager.Region) {
        flagList.postValue(xmlManager.changeCountriesList(region))
    }
}
