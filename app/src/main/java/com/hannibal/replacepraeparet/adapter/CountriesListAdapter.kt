package com.hannibal.replacepraeparet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannibal.replacepraeparet.model.Flag
import com.hannibal.replacepraeparet.R
import com.squareup.picasso.Picasso

class CountriesListAdapter(var flagList: List<Flag>): RecyclerView.Adapter<CountriesListAdapter.ViewHolder>() {
    private lateinit var listener: OnCountryCellClickListener

    interface OnCountryCellClickListener {
        fun onItemClick(flag: Flag)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.flag_image)
        val countryName: TextView = view.findViewById(R.id.country_name)
        val population: TextView = view.findViewById(R.id.population_text)
        val language: TextView = view.findViewById(R.id.language_text)
        val capital: TextView = view.findViewById(R.id.capital_text)
        val currency: TextView = view.findViewById(R.id.currency_text)
        val button: Button = view.findViewById(R.id.to_detail_button)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.countries_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flag = flagList[position]
        Picasso.get().load(flag.pictureId).resize(120, 80).into(holder.image)
        holder.countryName.text = flag.name
        holder.population.text = flag.population
        holder.language.text = flag.language
        holder.capital.text = flag.capital
        holder.currency.text = flag.currency
        holder.button.setOnClickListener {
            listener.onItemClick(flag)
        }
    }

    override fun getItemCount(): Int = flagList.size

    fun setOnCountryClickListener(listener: OnCountryCellClickListener) {
        this.listener = listener
    }
}