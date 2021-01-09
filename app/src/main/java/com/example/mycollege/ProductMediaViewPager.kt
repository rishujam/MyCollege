package com.example.mycollege

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.productmedia_viewpager.view.*

class ProductMediaViewPager(
        val images:List<Int>
) :RecyclerView.Adapter<ProductMediaViewPager.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.productmedia_viewpager,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImage =images[position]
        holder.itemView.ivProductMedia.setImageResource(currentImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}