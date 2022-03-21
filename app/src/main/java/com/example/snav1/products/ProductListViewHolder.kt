package com.example.snav1.products

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.snav1.MainActivity
import com.example.snav1.Product
import com.example.snav1.ProductListActivity
import com.example.snav1.R


class ProductListViewHolder(itemView:View,userType:String, itemClick:(position:Int,userType:String)->Unit,totalPrice:Double): RecyclerView.ViewHolder(itemView)
{
    var ivItemImage:ImageView
    var btnAdd:ImageButton
    var tvName:TextView
    var tvPrice:TextView

    var total_Price:Double=totalPrice
    var user_Type:String=userType

    init {
        ivItemImage=itemView.findViewById(R.id.ivProduct_C)
        tvName=itemView.findViewById(R.id.tvProductName_C)
        tvPrice=itemView.findViewById(R.id.tvPrice_C)
        btnAdd=itemView.findViewById(R.id.btnAdd_C)


        itemView.setOnClickListener { itemClick(adapterPosition,user_Type) }
    }

    fun bindData(context: Context,item: Product){

        //Bag and price visibility
        if (user_Type=="guest"){
            btnAdd.isVisible=false
        }

        tvName.text=item.Name
        tvPrice.text=item.Price.toString()
        //ivItemImage.setImageBitmap()

        btnAdd.setOnClickListener {
            total_Price+= item.Price
            Toast.makeText(context,"${tvName.text} ürününü sepetinize eklediniz",Toast.LENGTH_SHORT).show()


        }



    }
}

