package com.rplits.viper

import com.google.gson.annotations.SerializedName

class UniversityList {
    @SerializedName("list")
    var list: List<University> = emptyList()
}

class University {
    @SerializedName("state-province")
    var stateProvince: String = ""

    @SerializedName("country")
    var country: String = ""

    @SerializedName("alpha_two_code")
    var alphaTwoCode: String = ""

    @SerializedName("name")
    var name: String = ""
}