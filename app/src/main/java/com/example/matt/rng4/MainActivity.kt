package com.example.matt.rng4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        val firstParts = arrayListOf("B","Bh","Bl","Br","C","Ch","Chr","Cl","Cr","D","Dr","F","Fl","Fr","G","Gh","Gl","Gn","Gr","H","J","K","Kl","Kn","Kr","L","M","Mb","N","P","Ph","Phr","Pl","Pr","Qu","R","S","Sc","Sch","Scr","Sh","Shr","Shl","Sk","Sl","Sm","Sn","Sp","Spl","Spr","Squ","St","Str","Sw","T","Th","Thr","Tr","Ts","Tw","V","W","Wh","Wr","X","Y","Z")
        val vowels = arrayListOf("a","e","i","o","u")
        val lastParts = arrayListOf("b","bb","ch","ck","ct","d","dd","f","ff","ft","g","gg","gh","ld","lf","lk","ll","lm","lp","lt","m","mb","mm","mp","mph","n","nch","nd","ng","nk","nn","nt","nth","p","ph","pp","r","rb","rch","rd","rf","rg","rk","rl","rm","rn","rp","rst","rt","rth","sh","sk","sm","sp","ss","st","t","tch","th","tt","w","wn","x","zz")
        val suffixes = arrayListOf("","berry","dale","ford","ington","kin","ly","man","sky","smith","son","stein","ston","sworth","word","ham","wold","lord","berg","storm","strom")

        val random = Random()
        var firstName = ""
        var lastName = ""

        fun randfirst(){
            firstName = firstParts[random.nextInt(firstParts.count())] + vowels[random.nextInt(vowels.count())] + lastParts[random.nextInt(lastParts.count())]
        }

        fun randlast(){
            lastName = firstParts[random.nextInt(firstParts.count())] + vowels[random.nextInt(vowels.count())] + lastParts[random.nextInt(lastParts.count())] + suffixes[random.nextInt(suffixes.count())]
        }

        if (fullName.text == "First Last") {
            randfirst()
            randlast()
            fullName.text = firstName + " " + lastName
        }

        fullNameButton.setOnClickListener {
            randfirst()
            randlast()
            fullName.text = firstName + " " + lastName
        }


        firstNameButton.setOnClickListener {
            randfirst()
            fullName.text = firstName + " " + lastName
        }


        lastNameButton.setOnClickListener {
            randlast()
            fullName.text = firstName + " " + lastName
        }

        btnFavorites.setOnClickListener{
            val intent = Intent(applicationContext,FavoritesActivity::class.java)
            startActivity(intent)
        }

        saveNameButton.setOnClickListener ({
            if (fullName.text.toString().length > 0){
                val db = DatabaseHandler(context)
                val favorite = Favorite(fullName.text.toString())
                db.insertData(favorite)
                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(context,"Name is empty",Toast.LENGTH_SHORT).show()
        })


//        saveNameButton.setOnClickListener {
//            if (fullName.text.isEmpty()) {
//                Toast.makeText(this, "No name", Toast.LENGTH_LONG).show()
//            }
//            else {
//                val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)
//
//                val editor = mypref.edit()
//                val nNames = mypref.all.size
//
//                editor.putString((nNames + 1).toString(), fullName.text.toString())
//
//                editor.apply()
//
//                Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
//            }
//        }
    }
}
