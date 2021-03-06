package com.example.snav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import com.example.snav1.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    //product detail screen

    lateinit var binding: ActivityProductDetailBinding
    lateinit var product:Product
    lateinit var userType:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Status Bar Design
        window.statusBarColor=ContextCompat.getColor(this,R.color.white)
        WindowInsetsControllerCompat(window,window.decorView).isAppearanceLightStatusBars = true


        product= intent.getSerializableExtra("item") as Product
        userType = intent.getStringExtra("user_type").toString()
        var totalPrice = intent.getFloatExtra("total",0.0f)



        binding.btnBackD.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        if (userType == "guest"){
            binding.btnBagD.isVisible=false
            binding.tvBagPriceD.isVisible=false
            binding.btnAddD.isVisible=false
        }

        binding.tvBagPriceD.text= "₺" + totalPrice
        binding.tvProductDetailD.text=product.name
        binding.tvPriceD.text="₺"+product.price.toString()
        binding.ivProductD.setImageResource(product.img)
        binding.tvProductDesc.text=product.description



        binding.btnAddD.setOnClickListener {
            val intent= Intent()
            intent.putExtra("itemD",product)
            //cardTotal.add(product.price)
            setResult(RESULT_OK,intent)
            finish()
        }


    }



}