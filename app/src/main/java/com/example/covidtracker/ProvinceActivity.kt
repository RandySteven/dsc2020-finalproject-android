package com.example.covidtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.province.ApiClient
import com.example.covidtracker.province.datamodel.GetProvinceResponse
import com.example.covidtracker.province.model.Province
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_province.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinceActivity : AppCompatActivity() {
    private lateinit var provinceAdapter: ProvinceAdapter
    private lateinit var filteredList: ArrayList<Province>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_province)
        initAdapter()
        loadData()
        tv_back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun initAdapter(){
        provinceAdapter = ProvinceAdapter(ArrayList(), this)
        rv_provinces.layoutManager = LinearLayoutManager(this)
        rv_provinces.adapter = provinceAdapter
    }

    private fun loadData(){

        ApiClient.apiService.getProvince().enqueue(object : Callback<GetProvinceResponse> {
            override fun onFailure(call: Call<GetProvinceResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<GetProvinceResponse>,
                response: Response<GetProvinceResponse>
            ) {
                if(response.isSuccessful){
                    val result = response.body()?.data
                    val list = ArrayList<Province>()
                    if(!result.isNullOrEmpty()){
                        Log.d("<RESULT>", "onResponse : " + Gson().toJson(result))
                        result.forEach { provinceResponse ->
                            val province = Province(provinceResponse.provinsi, provinceResponse.kasusPosi, provinceResponse.kasusSemb, provinceResponse.kasusMeni)
                            list.add(province)
                        }
                        provinceAdapter.updateData(list)
                    }

                    et_search.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                            filteredList = ArrayList()
                            if(s.toString()!=""){
                                for(item in list){
                                    if(item.provinsi.toLowerCase().contains(s.toString().toLowerCase())){
                                        filteredList.add(item)
                                    }
                                }
                                provinceAdapter.updateData(filteredList)
                            }else{
                                provinceAdapter.updateData(list)
                            }
                        }
                    })
                }
            }
        })

    }

}