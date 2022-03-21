package com.example.snav1

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snav1.categry.CategoryAdapter
import com.example.snav1.databinding.ActivityProductListBinding
import com.example.snav1.products.ProductListViewHolder
import com.example.snav1.products.ProductsAdapter
import kotlinx.android.synthetic.main.category_card_design.*
import kotlinx.android.synthetic.main.product_card_design.*

class ProductListActivity : AppCompatActivity() {

    //product list screen

    lateinit var binding: ActivityProductListBinding
    var productList=ArrayList<Product>()

    lateinit var categoryList:ArrayList<String>
    lateinit var userType:String
    var totalPrice:Double = 0.0
    lateinit var productType:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Status Bar Design
        window.statusBarColor=ContextCompat.getColor(this,R.color.white)
        WindowInsetsControllerCompat(window,window.decorView).isAppearanceLightStatusBars = true

        userType= intent.getSerializableExtra("user_type").toString()

        categoryList= arrayListOf("su","soda","madensuyu","meyvesuyu","ayran")

        var product:Product


        var p1:Product= Product()
        var p3:Product=Product()
        var p2:Product=Product()

        p1.Id=1;p1.Name="1111";p1.Price=11.11;p1.Type="11"
        p2.Id=2;p2.Name="2222";p2.Price=22.11;p2.Type="22"
        p3.Id=3;p3.Name="3333";p3.Price=33.11;p3.Type="33"

        productList.add(p1);productList.add(p2);productList.add(p3)

        binding.btnBack.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }


        if (userType=="guest"){
            binding.btnBag.isVisible=false
            binding.tvBagPrice.isVisible=false
        }

        val lmCategory = LinearLayoutManager(this)
        lmCategory.orientation=LinearLayoutManager.HORIZONTAL
        binding.rvCategorys.layoutManager=lmCategory
        binding.rvCategorys.adapter=CategoryAdapter(this,categoryList,::categoryClick)


        val lmProducts=LinearLayoutManager(this)
        lmProducts.orientation=LinearLayoutManager.VERTICAL
        binding.rvProducts.layoutManager=lmProducts
        binding.rvProducts.adapter = ProductsAdapter(this,userType,productList,::itemClick ,totalPrice)




        binding.tvBagPrice.text=totalPrice.toString()





    }

    fun itemClick(position: Int,userType:String){
        val intent=Intent(this,ProductDetailActivity::class.java)
        intent.putExtra("item",productList.get(position))
        intent.putExtra("user_type",userType)
        resultLauncher.launch(intent)
    }

    fun categoryClick(position: Int){

        btnCategorys.setBackgroundResource(R.color.light_green)
        btnCategorys.setTextColor(Color.WHITE)



    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
        if (result.resultCode== RESULT_CANCELED)
        {
            Toast.makeText(this,"İşlemi iptal Ettiniz",Toast.LENGTH_SHORT).show()
        }
        else if (result.resultCode== RESULT_OK){
            Toast.makeText(this,"Ürün Ekleme Başarılı",Toast.LENGTH_LONG).show()
            var item=result.data!!.getSerializableExtra("item") as Product
            //totalPrice+= item.Price
        }

    }




}