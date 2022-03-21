package com.example.snav1.categry

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.snav1.R

class CategoryAdapter (val context: Context, var cList:ArrayList<String>,val categoryClick:(position:Int)->Unit) : RecyclerView.Adapter<CategoryWiewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryWiewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.category_card_design,parent,false)
        return CategoryWiewHolder(v,categoryClick)
    }

    override fun onBindViewHolder(holder: CategoryWiewHolder, position: Int) {
        holder.bindData(context,cList.get(position))    }

    override fun getItemCount(): Int {
        return cList.size
    }


}