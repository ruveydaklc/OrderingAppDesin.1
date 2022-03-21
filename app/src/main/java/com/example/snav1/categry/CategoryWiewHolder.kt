package com.example.snav1.categry

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.snav1.R

class CategoryWiewHolder (itemView: View,itemClick:(position:Int)->Unit): RecyclerView.ViewHolder(itemView){

    var btnCategory:Button

    init {
        btnCategory=itemView.findViewById(R.id.btnCategorys)

        itemView.setOnClickListener { itemClick(adapterPosition) }
    }
    fun bindData(context:Context,item:String){

        btnCategory.text=item




        btnCategory.setOnClickListener {
            if (btnCategory.text==item){

            }
            btnCategory.setBackgroundResource(R.color.light_green)
            btnCategory.setTextColor(Color.WHITE)
            Toast.makeText(context,"çalışıyor",Toast.LENGTH_SHORT).show()
        }
    }
}