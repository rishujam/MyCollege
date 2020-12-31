package com.example.mycollege

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_products.view.*


class ItemAdapter(
        private var item:List<Item>,
        private val listener :OnItemClickListener
):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    inner class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position =adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }
    interface  OnItemClickListener{
        fun onItemClick(position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view  =LayoutInflater.from(parent.context).inflate(R.layout.item_products,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.apply {
            ivProductImage.setImageResource(item[position].image)
            tvProductTitle.text=item[position].title
            tvProductDiscription.text= item[position].description        }
    }
}