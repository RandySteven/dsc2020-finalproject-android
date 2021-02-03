package com.example.covidtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.covid.ApiClient
import com.example.covidtracker.covid.model.Covid
import com.example.covidtracker.province.datamodel.GetProvinceResponse
import com.example.covidtracker.province.model.Province
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.province_list_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionButton()
        initAdapter()
        loadData()
//        val number: TextView = tv_numbs
//        for(i in 0..2){
//            number.setText(i.toString())
//        }
    }

    private fun actionButton(){
        btn_see_provinces.setOnClickListener {
            startActivity(Intent(this, ProvinceActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun getData(){
        ApiClient.apiService.getData().enqueue(object : Callback<Covid> {
            override fun onFailure(call: Call<Covid>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Covid>, response: Response<Covid>) {
                if(response.isSuccessful){
                    val covid: Covid? = response.body()
                    setResponse(covid)
                }
            }
        })
    }

    private fun setResponse(covid: Covid?){
        val response = covid
        Log.d("<RESULT>", "onResponse : " + Gson().toJson(response))
        val deaths = response?.deaths?.value
        val recovered = response?.recovered?.value
        val positives = response?.confirmed?.value
        val total = deaths!! + recovered!! + positives!!
        tv_death_data.text = deaths.toString()
        tv_positives_data.text = positives.toString()
        tv_recovered_data.text = recovered.toString()
        tv_total_data.text = total.toString()
    }

    private lateinit var provinceListAdapter: ProvinceListAdapter
    private fun initAdapter(){
        provinceListAdapter = ProvinceListAdapter(ArrayList(), this)
        rv_province_list.layoutManager = LinearLayoutManager(this)
        rv_province_list.adapter = provinceListAdapter
    }

    private fun loadData(){
        com.example.covidtracker.province.ApiClient.apiService.getProvince().enqueue(object : Callback<GetProvinceResponse>{
            override fun onFailure(call: Call<GetProvinceResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<GetProvinceResponse>, response: Response<GetProvinceResponse>) {
                if(response.isSuccessful){
                    val result = response.body()?.data
                    val list = ArrayList<Province>()
                    if(!result.isNullOrEmpty()){
                        var i: Int
                        for(i in 0..2){
                            val province = Province(result[i].provinsi, result[i].kasusPosi, result[i].kasusSemb, result[i].kasusMeni)
                            list.add(province)
                        }
                        provinceListAdapter.updateData(list)
                    }
                }
            }

        })
    }
}