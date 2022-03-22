package com.example.snav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import com.example.snav1.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    //product detail screen

    lateinit var binding: ActivityProductDetailBinding
    lateinit var product:Product
    lateinit var userType:String
    var totalPrice:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Status Bar Design
        window.statusBarColor=ContextCompat.getColor(this,R.color.white)
        WindowInsetsControllerCompat(window,window.decorView).isAppearanceLightStatusBars = true


        product= intent.getSerializableExtra("item") as Product
        userType=intent.getStringExtra("user_type").toString()
        //totalPrice=intent.getDoubleExtra("total")


        binding.btnBackD.setOnClickListener {
            finish()
        }

        binding.tvBagPriceD.text= "₺" + totalPrice
        binding.tvProductDetailD.text=product.name
        binding.tvPriceD.text="₺"+product.price.toString()
        binding.ivProductD.setImageResource(product.img)
        binding.tvProductDesc.text=product.description

        if (userType=="guest"){
            binding.btnAddD.isVisible=false
            binding.tvBagPriceD.isVisible=false
            binding.btnAddD.isVisible=false
        }

        binding.btnAddD.setOnClickListener {
            val intent= Intent()
            intent.putExtra("item",product)
            Toast.makeText(this,"Sepetinize ${product.name} ürününü eklediniz",Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK,intent)
            finish()
        }


    }



}