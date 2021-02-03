package com.example.covidtracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.province.model.Province
import kotlinx.android.synthetic.main.province_item.view.*
import kotlinx.android.synthetic.main.province_list_item.view.*

class ProvinceListAdapter(private val provinces: ArrayList<Province>, private val context: Context): RecyclerView.Adapter<ProvinceListAdapter.ProvinceListViewHolder>() {
    inner class ProvinceListViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val provinsi: TextView = view.tv_province

        fun bindProvince(province: Province){
            provinsi.text = province.provinsi
        }
    }

    fun updateData(newProvinces: ArrayList<Province>){
        this.provinces.clear()
        this.provinces.addAll(newProvinces)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceListViewHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        val view = LayoutInflater.inflate(R.layout.province_list_item, parent, false)
        return ProvinceListViewHolder(view)
    }

    override fun getItemCount(): Int = provinces.size

    override fun onBindViewHolder(holder: ProvinceListViewHolder, position: Int) {
        val province = provinces[position]
        return holder.bindProvince(province)
    }
}