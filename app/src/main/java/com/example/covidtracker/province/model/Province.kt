package com.example.covidtracker.province.model

data class Province(
    val fid: Int,
    val kodeProvi: Int,
    val provinsi: String,
    val kasusPosi: Int,
    val kasusSemb: Int,
    val kasusMeni: Int
){
    constructor(
        provinsi: String,
        kasusPosi: Int,
        kasusSemb: Int,
        kasusMeni: Int
    ): this(0, 0, provinsi, kasusPosi, kasusSemb, kasusMeni)
}