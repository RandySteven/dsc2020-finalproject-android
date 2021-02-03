package com.example.covidtracker.province.datamodel

data class GetProvinceResponse(
    val perawatan: Int,
    val sembuh: Int,
    val meninggal: Int,
    val jumlahKasus: Int,
    val data: ArrayList<ProvinceItemResponse>
)

data class ProvinceItemResponse(
    val fid: Int,
    val kodeProvi: Int,
    val provinsi: String,
    val kasusPosi: Int,
    val kasusSemb: Int,
    val kasusMeni: Int
)