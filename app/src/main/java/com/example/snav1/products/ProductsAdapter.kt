package com.example.snav1.products

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.snav1.Product
import com.example.snav1.R

class ProductsAdapter(
    val context: Context, var userType:String
    , var pList:ArrayList<Product>
    , var cList:ArrayList<Product>
    , val itemClick : (position : Int)->Unit) : RecyclerView.Adapter<ProductListViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {

        val v =LayoutInflater.from(context).inflate(R.layout.product_card_design,parent,false)
        return ProductListViewHolder(v,userType,itemClick)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {

        holder.bindData(context,pList.get(position),cList)
    }

    override fun getItemCount(): Int {
        return pList.size

    }



}