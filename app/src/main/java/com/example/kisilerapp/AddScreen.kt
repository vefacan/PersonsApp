package com.example.kisilerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.room.Room
import com.example.kisilerapp.databinding.ActivityAddScreenBinding
import com.example.kisilerapp.databinding.ActivityMainBinding
import com.example.kisilerapp.room.Person
import com.example.kisilerapp.room.PersonDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddScreen : AppCompatActivity() {

    private lateinit var binding: ActivityAddScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val popupMenu = PopupMenu(this, binding.btnSelectGroup)

        popupMenu.menu.add(Menu.NONE, 0, 0, "Colleagues")
        popupMenu.menu.add(Menu.NONE, 1, 1, "Family")
        popupMenu.menu.add(Menu.NONE, 2, 2, "Friends")
        popupMenu.menu.add(Menu.NONE, 3, 3, "School")

        var selectedGroup = ""

        popupMenu.setOnMenuItemClickListener {

            val id = it.itemId

            if (id == 0) {
                Toast.makeText(this@AddScreen, "Colleagues Chosen", Toast.LENGTH_SHORT).show()
                selectedGroup = "Colleagues"
            } else if (id == 1) {
                Toast.makeText(this@AddScreen, "Family Chosen", Toast.LENGTH_SHORT).show()
                selectedGroup = "Family"
            } else if (id == 2) {
                Toast.makeText(this@AddScreen, "Friends Chosen", Toast.LENGTH_SHORT).show()
                selectedGroup = "Friends"
            } else if (id == 3) {
                Toast.makeText(this@AddScreen, "School Chosen", Toast.LENGTH_SHORT).show()
                selectedGroup = "School"
            }
            false
        }

        binding.btnSelectGroup.setOnClickListener {
            popupMenu.show()
        }

        binding.btnAddPerson.setOnClickListener { view ->

            val name = binding.editTxtName.text.toString()
            val surname = binding.editTxtSurname.text.toString()
            val telephone = binding.editTxtPhone.text.toString()
            val adress = binding.EditTxtAdress.text.toString()

            if (selectedGroup == ""){
                Toast.makeText(this@AddScreen,"Please select a group",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (name.isNotEmpty() && surname.isNotEmpty() && telephone.isNotEmpty() && adress.isNotEmpty()) {

                val db = Room.databaseBuilder(
                    applicationContext,
                    PersonDatabase::class.java,
                    "appDataBase"
                ).build()

                val run = Runnable {
                    val person = Person(
                        null,
                        person_name = name,
                        person_surname = surname,
                        person_phone = telephone,
                        person_adress = adress,
                        person_group = selectedGroup
                    )
                    db.getPersonsDao().addPerson(person)
                }
                Thread(run).start()
            }

            Snackbar.make(view, "Person Added Successfully", Snackbar.LENGTH_SHORT).show()
            GlobalScope.launch(Dispatchers.Main) {
                delay(2000) // 2 saniye gecikme
                val intentToMainActivity = Intent(this@AddScreen, MainActivity::class.java)
                startActivity(intentToMainActivity)
            }
        }

    }

}