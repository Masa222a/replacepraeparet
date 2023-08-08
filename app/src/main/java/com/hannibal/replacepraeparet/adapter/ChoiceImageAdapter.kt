package com.hannibal.replacepraeparet.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hannibal.replacepraeparet.R
import com.squareup.picasso.Picasso

class ChoiceImageAdapter(var imageList: MutableList<Uri>): RecyclerView.Adapter<ChoiceImageAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.choice_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.choice_image_row_in_bottom_sheet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = imageList[position]
        Picasso.get().load(item).resize(50, 50).into(holder.image)
    }

    override fun getItemCount() = imageList.size
}