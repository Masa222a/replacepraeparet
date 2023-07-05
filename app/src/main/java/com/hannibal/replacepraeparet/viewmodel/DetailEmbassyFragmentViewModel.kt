package com.hannibal.replacepraeparet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hannibal.replacepraeparet.model.Embassy
import com.hannibal.replacepraeparet.model.Flag
import com.hannibal.replacepraeparet.model.ScrapingManager
import com.hannibal.replacepraeparet.model.XmlManager
import kotlinx.coroutines.launch

class DetailEmbassyFragmentViewModel : ViewModel() {
    var embassyData = MutableLiveData<MutableList<Embassy>>()
    private val scrapingManager = ScrapingManager()

    fun getEmbassyData(flag: Flag) {
        viewModelScope.launch {
            val url = ScrapingManager.UrlCreate(XmlManager.Region.indexOf(flag.region), flag).mainUrl
            embassyData.postValue(scrapingManager.fetchUrl(url))
        }
    }
}