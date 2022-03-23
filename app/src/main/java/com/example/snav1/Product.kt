package com.example.snav1

import android.media.Image
import android.widget.ImageView
import java.io.Serializable

class Product(var id:Int=0,
              var name:String?=null,
              var price:Float=0.0f,
              var type:String?=null,
              var img:Int,
              var description:String?= "Ürüne detaylı açıklaması", ) :Serializable{

}