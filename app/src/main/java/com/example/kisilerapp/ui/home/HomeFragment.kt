package com.example.kisilerapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.kisilerapp.DetailPage
import com.example.kisilerapp.databinding.FragmentHomeBinding
import com.example.kisilerapp.room.Person
import com.example.kisilerapp.room.PersonDatabase
import com.example.kisilerapp.ui.adapter.PersonAdapter
import kotlinx.coroutines.*


class HomeFragment : Fragment() {

    private lateinit var db: PersonDatabase
    private var _binding: FragmentHomeBinding? = null
    private var adapter: PersonAdapter? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        db = Room.databaseBuilder(
            requireContext(),
            PersonDatabase::class.java,
            "appDataBase"
        ).build()

        binding.homepageRecyclerview.setHasFixedSize(true)
        binding.homepageRecyclerview.layoutManager = LinearLayoutManager(context)
        adapter = PersonAdapter()
        binding.homepageRecyclerview.adapter = adapter


        GlobalScope.launch {
            launch(Dispatchers.IO) {
                val status = db.getPersonsDao().getLastTenPersons()
                adapter?.addItems(status as ArrayList<Person>)
                withContext(Dispatchers.Main) {
                    adapter?.notifyDataSetChanged()
                }
            }
        }


        binding.editTxtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val search = "%${s}%"
                GlobalScope.launch {
                    launch(Dispatchers.IO) {
                        val persons = db.getPersonsDao().searchPerson(search)
                        withContext(Dispatchers.Main) {
                            adapter?.addItems(persons as ArrayList<Person>)
                            adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        adapter?.setOnClickItem {
            val intent = Intent(context, DetailPage::class.java)
            intent.putExtra("personId", it.person_id.toString())
            intent.putExtra("personName", it.person_name)
            intent.putExtra("personSurname", it.person_surname)
            intent.putExtra("personTelephone", it.person_phone)
            intent.putExtra("personAdress", it.person_adress)
            intent.putExtra("personGroup", it.person_group)
            startActivity(intent)
        }

        return root
    }
}


