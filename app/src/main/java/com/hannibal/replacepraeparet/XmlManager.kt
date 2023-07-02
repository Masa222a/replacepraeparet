package com.hannibal.replacepraeparet

import org.xmlpull.v1.XmlPullParser

class XmlManager {
    private val context = MyApplication.applicationContext()!!
    var flags = listOf<Flag>()
    var visaList = mutableListOf<Visa>()
    init {
        getXmlData()
        getVisaData()
    }

    enum class Region(val index: Int) {
        Asia(0),
        Oceania(1),
        NorthAmerica(2),
        CentralNorthAmerica(3),
        Europe(4),
        MiddleEast(5),
        Africa(6);

        companion object {
            fun indexOf(position: Int): Region {
                return values().first { it.index == position }
            }
        }

        val countryCodes: List<Int>
            get() = when(this) {
                //1..25アジア
                Asia -> (1..25).toList()
                //26..41オセアニア
                Oceania -> (26..41).toList()
                //42..43北米
                NorthAmerica -> (42..43).toList()
                //44..76中・南米
                CentralNorthAmerica -> (44..76).toList()
                //77..130ヨーロッパ
                Europe -> (77..130).toList()
                //131..145中東
                MiddleEast -> (131..145).toList()
                // 146..199アフリカ
                Africa -> (146..199).toList()
            }

        val urlPath: String
            get() {
                return when(this) {
                    Asia -> "asia"
                    Oceania -> "oceania"
                    NorthAmerica -> "n_ame"
                    CentralNorthAmerica -> "cs_ame"
                    Europe -> "europe"
                    MiddleEast -> "nm_east"
                    Africa -> "africa"
                }
            }
    }

    fun changeCountriesList(regions: Region): List<Flag> {
        return flags.filter { regions.countryCodes.contains(it.id) }
    }

    private fun getXmlData() {
        val parser = context.resources.getXml(R.xml.country_information)
        val flags = mutableListOf<Flag>()
        var tagName: String? = null
        var eventType = parser.eventType

        val countryCodes = mutableListOf<Int>()
        val countriesName = mutableListOf<String>()
        val countriesEngName = mutableListOf<String>()
        val pictureIds = mutableListOf<Int>()
        val populations = mutableListOf<String>()
        val languages = mutableListOf<String>()
        val capitals = mutableListOf<String>()
        val currencies = mutableListOf<String>()
        val regions = mutableListOf<Int>()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            when(eventType) {
                XmlPullParser.START_DOCUMENT -> {

                }
                XmlPullParser.START_TAG -> {
                    tagName = parser.name
                    when (tagName) {
                        "country" -> {
                            countryCodes.add(parser.getAttributeIntValue(null, "country_code", 0))
                        }
                    }
                }
                XmlPullParser.TEXT -> {
                    if(tagName != null) {
                        when(tagName) {
                            "name" -> {
                                countriesName.add(parser.text)
                            }
                            "flag" -> {
                                countriesEngName.add(parser.text)
                                pictureIds.add(
                                    context.resources.getIdentifier(
                                        parser.text,
                                        "drawable",
                                        context.packageName
                                    )
                                )
                            }
                            "population" -> {
                                populations.add(parser.text)
                            }
                            "language" -> {
                                languages.add(parser.text)
                            }
                            "capital" -> {
                                capitals.add(parser.text)
                            }
                            "currency" -> {
                                currencies.add(parser.text)
                            }
                            "region" -> {
                                regions.add(parser.text.toInt())
                            }
                        }
                    }
                }
            }
            eventType = parser.next()
        }

        parser.close()

        for(i in 0 until pictureIds.size) {
            flags.add(Flag(countryCodes[i], pictureIds[i], countriesName[i], countriesEngName[i], populations[i], languages[i], capitals[i], currencies[i], regions[i]))
        }
        this.flags = flags
    }

    fun changeVisaList(id: Int): List<Visa> {
        return visaList.filter { it.id == id }
    }

    fun getVisaData() {
        val parser = context.resources.getXml(R.xml.country_visa_information)
        val dataList = mutableListOf<Visa>()
        var tagName: String? = null
        var eventType = parser.eventType

        val visaName = mutableListOf<String>()
        val visaContent = mutableListOf<String>()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            when(eventType) {
                XmlPullParser.START_DOCUMENT -> {

                }
                XmlPullParser.START_TAG -> {
                    tagName = parser.name
                }
                XmlPullParser.TEXT -> {
                    if (tagName != null) {
                        when(tagName) {
                            "flag" -> {
                                visaName.add(parser.text)
                            }
                            "visa" -> {
                                visaContent.add(parser.text)
                            }
                        }
                    }
                }
            }
            eventType = parser.next()
        }

        parser.close()

        for (i in 1 until visaName.size + 1) {
            dataList.add(Visa(i, visaName[i - 1], visaContent[i - 1]))
        }

        this.visaList = dataList
    }
}
