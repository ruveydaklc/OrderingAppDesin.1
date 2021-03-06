package com.example.snav1

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snav1.card.cardTotal
import com.example.snav1.databinding.ActivityProductListBinding
import com.example.snav1.products.ProductsAdapter

open class ProductListActivity : AppCompatActivity() {

    //product list screen

    lateinit var binding: ActivityProductListBinding
    var productList=ArrayList<Product>()

    lateinit var userType:String
    var totalPrice:Float = 0.00f
    var selectedFilter:String="all"

    var white :Int =0
    var lightGreen:Int = 0
    var allButton=ArrayList<Button>()
    var fCategory:String="water"

    //val productsLiveData = MutableLiveData<List<Product>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Status Bar Design
        window.statusBarColor=ContextCompat.getColor(this,R.color.white)
        WindowInsetsControllerCompat(window,window.decorView).isAppearanceLightStatusBars = true

        userType= intent.getStringExtra("user_type").toString()

        setTotalPrice()
        addingProductList()
        initColors()
        addingButtons()


        binding.btnBack.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        if (userType=="guest"){
            binding.btnBag.isVisible=false
            binding.tvBagPrice.isVisible=false
        }



        //set "water" button first
        filterList(fCategory);unSelectedAllFilterButtons(binding.btnWater)
        //showAllData(productList) //for set all product first

        binding.btnAyran.setOnClickListener { ayranTapp(binding.btnAyran) }
        binding.btnCoffee.setOnClickListener { coffeeTapp(binding.btnCoffee) }
        binding.btnJuice.setOnClickListener { juiceTapp(binding.btnJuice) }
        binding.btnMinWater.setOnClickListener { minWaterTapp(binding.btnMinWater) }
        binding.btnSoda.setOnClickListener { sodaTapp(binding.btnSoda) }
        binding.btnWater.setOnClickListener { waterTapp(binding.btnWater) }

    }

    //category filter
    private fun filterList(category:String){

        setTotalPrice()
        selectedFilter=category
        var categoryFilterList=ArrayList<Product>()
        for (item in productList){
            if (item.type == category){
                categoryFilterList.add(item)
            }
        }
        val lmProducts = GridLayoutManager(this,4)
        lmProducts.orientation=LinearLayoutManager.VERTICAL
        binding.rvProducts.layoutManager=lmProducts
        binding.rvProducts.adapter = ProductsAdapter(this,userType,categoryFilterList,::itemClick,::addClick)

    }

    /*fun showAllData(product_list:ArrayList<Product>){
        setTotalPrice()
        binding.rvProducts.adapter = ProductsAdapter(this,userType,product_list,::itemClick,::addClick)
    }*/

    //button clicked
    private fun categorySelected(parsedButton:Button){
        parsedButton.setTextColor(white)
        parsedButton.setBackgroundColor(lightGreen)
    }
    private fun categoryUnSelected(parsedButtons:ArrayList<Button>){
        for (button in parsedButtons){
            button.setTextColor(lightGreen)
            button.setBackgroundColor(white)
        }
    }
    private fun unSelectedAllFilterButtons(btn:Button){
        var newButtonList=ArrayList<Button>()
        newButtonList.addAll(allButton)
        categorySelected(btn)
        for (button in allButton){
            if (btn==button){
                newButtonList.remove(button)
                categoryUnSelected(newButtonList)
            }
        }
    }


    //button click functions
    fun waterTapp(btn:Button){ filterList("water") ;unSelectedAllFilterButtons(btn)}
    fun sodaTapp(btn:Button){ filterList("soda");unSelectedAllFilterButtons(btn)}
    fun minWaterTapp(btn:Button){ filterList("minWater");unSelectedAllFilterButtons(btn) }
    fun ayranTapp(btn:Button){ filterList("ayran");unSelectedAllFilterButtons(btn) }
    fun juiceTapp(btn:Button){ filterList("juice");unSelectedAllFilterButtons(btn)}
    fun coffeeTapp(btn:Button){ filterList("coffee");unSelectedAllFilterButtons(btn)}

    private fun setTotalPrice( ){
        totalPrice=0.00f
        for (i in cardTotal){
            totalPrice = totalPrice + i
        }
        totalPrice = Math.round(totalPrice * 100.0f) / 100.00f
        binding.tvBagPrice.text="???"+ totalPrice
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::reResult )
    fun reResult(result: ActivityResult){
        if (result.resultCode== RESULT_OK){
            val p =result.data!!.getSerializableExtra("itemD") as Product
            cardTotal.add(p.price)
            Toast.makeText(this,"Sepetinize ${p.name} ??r??n??n?? eklediniz", Toast.LENGTH_SHORT).show()
            totalPrice+= p.price
            binding.tvBagPrice.text="???"+ totalPrice

        }

        if (result.resultCode== RESULT_CANCELED){
            binding.tvBagPrice.text="???"+ totalPrice

        }

        binding.rvProducts.adapter!!.notifyDataSetChanged()
        setTotalPrice()
    }

    fun itemClick(position : Int,list:ArrayList<Product>) {
        setTotalPrice()
        val intent= Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("item",list.get(position))
        intent.putExtra("user_type",userType)
        intent.putExtra("total",totalPrice)
        resultLauncher.launch(intent)
    }
    fun addClick(position: Int,list: ArrayList<Product>){
        cardTotal.add(list.get(position).price)
        setTotalPrice()
        Toast.makeText(this,"${list.get(position).name} ??r??n?? sepetinize eklenmi??tir.",Toast.LENGTH_SHORT).show()
    }


    fun addingButtons(){
        allButton.add(binding.btnAyran)
        allButton.add(binding.btnCoffee)
        allButton.add(binding.btnJuice)
        allButton.add(binding.btnMinWater)
        allButton.add(binding.btnSoda)
        allButton.add(binding.btnWater)
    }
    fun initColors(){
        white=ContextCompat.getColor(applicationContext,R.color.white)
        lightGreen=ContextCompat.getColor(applicationContext,R.color.light_green)
    }


    //product data
    fun addingProductList(){

        val p1:Product= Product(1,"Damla Su",10.99f,"water",R.mipmap.damla_su_foreground,"Damla Su Pet 1 L")
        val p3:Product=Product(2,"S??ta?? Ayran",11.99f,"ayran",R.mipmap.sutas_ayran_foreground,"????ecek Kategorisi Ayran")
        val p2:Product=Product(3,"Cappy Meyve Suyu",12.99f,"juice",R.mipmap.cappy_meyvesuyu_foreground)
        val p4:Product= Product(4,"Erikli Su",13.99f,"water",R.mipmap.erikli_su_foreground)
        val p5:Product= Product(5,"Saka Su",14.99f,"water",R.mipmap.saka_su_foreground)
        val p6:Product= Product(6,"Neskafe Kahve",15.99f,"coffee",R.mipmap.neskafe_kahve_foreground,"Nescafe Xpress Karamel Aromal?? Kahveli S??tl?? ????ecek 250 ml")
        val p7:Product= Product(7,"Perrier Madensuyu",16.99f,"minWater",R.mipmap.perrier_madensuyu_foreground,"Perrier Maden Suyu 33 Cl")
        val p8:Product= Product(8,"Schweppes",17.99f,"soda",R.mipmap.schweppes_soda_foreground,"Schweppes Mandalina Aromal?? Gazl?? ????ecek Cam 250 Ml")
        val p9:Product= Product(9,"P??nar Su",18.99f,"water",R.mipmap.pinar_su_foreground,"P??nar Su 1 L")


        productList.add(p1);productList.add(p2);productList.add(p3);productList.add(p4);productList.add(p5)
        productList.add(p6);productList.add(p7);productList.add(p8);productList.add(p9)

    }



}