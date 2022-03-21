package com.example.snav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.snav1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Login screen

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val sampPhoneNum:String = "01111111111"
        val sampPassword:String= "#e&it1m"

        */

        val sampPhoneNum:String = "0"
        val sampPassword:String= "0"

        binding.btnLogIn.setOnClickListener {

            if (eTvPhone.text.toString() == sampPhoneNum && eTvPasword.text.toString() == sampPassword){
                Toast.makeText(this,"Giriş Başarılı.",Toast.LENGTH_SHORT).show()
                var intent=Intent(this,ProductListActivity::class.java)
                intent.putExtra("user_type","user")
                resultLauncher.launch(intent)

            }
            else{
                AlertWarning()
            }

        }


        binding.btnLoginNot.setOnClickListener {
            Toast.makeText(this,"Misafir olarak giriş yaptınız.",Toast.LENGTH_SHORT).show()

            var intent = Intent(this,ProductListActivity::class.java)
            intent.putExtra("user_type","guest")

            resultLauncher.launch(intent)
        }

    }


    fun AlertWarning(){
        val adb:AlertDialog.Builder=AlertDialog.Builder(this)
        adb.setTitle("Giriş Hatası!! ")
        adb.setMessage("Telefon numaranız ile şifreniz uyuşmuyor.Tekrar deneyiniz.")
        adb.setPositiveButton("Tamam",null)
        adb.show()
    }



    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.resultCode== RESULT_CANCELED)
        {
            Toast.makeText(this, "Giriş Sayfasına Döndünüz", Toast.LENGTH_SHORT).show()
        }

    }

}