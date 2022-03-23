package com.example.snav1.products

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.snav1.Product
import com.example.snav1.R
import com.example.snav1.card.cardTotal


class ProductListViewHolder(itemView:View,userType:String,pList:ArrayList<Product>, itemClick : (position : Int,list:ArrayList<Product>)->Unit): RecyclerView.ViewHolder(itemView)
{
    var ivItemImage:ImageView
    var btnAdd:ImageButton
    var tvName:TextView
    var tvPrice:TextView
    var cardView:CardView



    var user_Type:String=userType
    init {
        ivItemImage=itemView.findViewById(R.id.ivProduct_C)
        tvName=itemView.findViewById(R.id.tvProductName_C)
        tvPrice=itemView.findViewById(R.id.tvPrice_C)
        btnAdd=itemView.findViewById(R.id.btnAdd_C)
        cardView=itemView.findViewById(R.id.card_product_design)

        itemView.setOnClickListener { itemClick(adapterPosition,pList) }


    }

    fun bindData(
        context: Context,
        item: Product){

        //Bag and price visibility
        if (user_Type == "guest"){
            btnAdd.isVisible=false
        }

        tvName.text=item.name
        tvPrice.text="₺"+ item.price.toString()

        val image = BitmapFactory.decodeResource(context.resources, item.img!!)
        ivItemImage.setImageBitmap(image)

        btnAdd.setOnClickListener {
            cardTotal.add(item.price)
            Toast.makeText(context,"${tvName.text} ürününü sepetinize eklediniz",Toast.LENGTH_SHORT).show()
        }

        /*cardView.setOnClickListener {
            val intent= Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("item",item)
            intent.putExtra("user_type",user_Type)
            context.startActivity(intent)
        }*/



    }

}

