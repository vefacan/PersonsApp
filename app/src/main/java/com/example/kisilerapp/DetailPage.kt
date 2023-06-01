package com.example.kisilerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.kisilerapp.databinding.ActivityAddScreenBinding
import com.example.kisilerapp.databinding.ActivityDetailPageBinding
import com.example.kisilerapp.room.Person
import com.example.kisilerapp.room.PersonDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailPage : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val personId = intent.getStringExtra("personId")
        val personName = intent.getStringExtra("personName")
        val personSurname = intent.getStringExtra("personSurname")
        val personTelephone = intent.getStringExtra("personTelephone")
        val personAdress = intent.getStringExtra("personAdress")
        val personGroup = intent.getStringExtra("personGroup")

        binding.txtDetailPageName.setText(personName)
        binding.txtDetailPageSurname.setText(personSurname)
        binding.txtDetailPageTelephone.setText(personTelephone)
        binding.txtDetailPageAdress.setText(personAdress)
        binding.txtDetailPageGroup.setText(personGroup)


        binding.btnDelete.setOnClickListener { view ->

            val run = Runnable {
                val db = Room.databaseBuilder(
                    applicationContext,
                    PersonDatabase::class.java,
                    "appDataBase"
                ).build()

                val person = Person(
                    personId!!.toInt(),
                    personName,
                    personSurname,
                    personTelephone,
                    personAdress,
                    personGroup
                )
                db.getPersonsDao().deletePerson(person)
            }
            Thread(run).start()

            Snackbar.make(view, "Person Deleted Succesfully", Snackbar.LENGTH_SHORT).show()
            GlobalScope.launch(Dispatchers.Main) {
                delay(2000) // 2 saniye gecikme
                val intentToMainActivity = Intent(this@DetailPage, MainActivity::class.java)
                startActivity(intentToMainActivity)
            }
        }

        binding.btnUpdate.setOnClickListener { view ->

            val run = Runnable {
                val db = Room.databaseBuilder(
                    applicationContext,
                    PersonDatabase::class.java,
                    "appDataBase"
                ).build()

                val person = Person(
                    personId!!.toInt(),
                    personName,
                    personSurname,
                    personTelephone,
                    personAdress,
                    personGroup
                )
                db.getPersonsDao().deletePerson(person)
            }
            Thread(run).start()

            Snackbar.make(view, "Please Update the Person", Snackbar.LENGTH_SHORT).show()
            GlobalScope.launch(Dispatchers.Main) {
                delay(2000) // 2 saniye gecikme
                val intentToMainActivity = Intent(this@DetailPage, AddScreen::class.java)
                startActivity(intentToMainActivity)
            }
        }
    }
}