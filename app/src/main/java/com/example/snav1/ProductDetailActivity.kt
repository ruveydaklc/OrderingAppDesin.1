package com.example.snav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import com.example.snav1.databinding.ActivityMainBinding
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
        userType=intent.getStringExtra("user_type").toString()


        binding.btnBackD.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
        binding.tvProductDetailD.text=product.Name
        binding.tvPriceD.text=product.Price.toString()

        if (userType=="guest"){
            binding.btnAddD.isVisible=false
            binding.tvBagPriceD.isVisible=false
            binding.btnAddD.isVisible=false
        }

        binding.btnAddD.setOnClickListener {
            val intent= Intent()
            intent.putExtra("item",product)
            setResult(RESULT_OK,intent)
            finish()
        }





    }
}