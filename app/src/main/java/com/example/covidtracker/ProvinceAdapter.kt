package com.example.covidtracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.province.model.Province
import kotlinx.android.synthetic.main.province_item.view.*

class ProvinceAdapter (private val provinces: ArrayList<Province>, private val context: Context): RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>(){

    inner class ProvinceViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val provinsi: TextView = view.tv_province_name
        private val deaths: TextView = view.tv_death_data
        private val recovered: TextView = view.tv_recovered_data
        private val positives: TextView = view.tv_positives_data

        fun bindProvince(province: Province){
            provinsi.text = province.provinsi
            deaths.text = province.kasusMeni.toString()
            recovered.text = province.kasusSemb.toString()
            positives.text = province.kasusPosi.toString()
        }
    }

    fun updateData(newProvinces: ArrayList<Province>){
        this.provinces.clear()
        this.provinces.addAll(newProvinces)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        val view = LayoutInflater.inflate(R.layout.province_item, parent, false)
        return ProvinceViewHolder(view)
    }

    override fun getItemCount(): Int = provinces.size

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val province = provinces[position]
        return holder.bindProvince(province)
    }


}