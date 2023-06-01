package com.example.kisilerapp.ui.colleagues

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.kisilerapp.DetailPage
import com.example.kisilerapp.databinding.FragmentColleaguesBinding
import com.example.kisilerapp.room.Person
import com.example.kisilerapp.room.PersonDatabase
import com.example.kisilerapp.ui.adapter.PersonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ColleaguesFragment : Fragment() {

    private var _binding: FragmentColleaguesBinding? = null
    private var adapter: PersonAdapter? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentColleaguesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val db = Room.databaseBuilder(
            requireContext(),
            PersonDatabase::class.java,
            "appDataBase"
        ).build()

        binding.colleaguesRecyclerview.layoutManager = LinearLayoutManager(context)
        adapter = PersonAdapter()
        binding.colleaguesRecyclerview.adapter = adapter

        GlobalScope.launch {
            launch(Dispatchers.IO) {
                val status = db.getPersonsDao().getGroupPersons("Colleagues")
                adapter?.addItems(status as ArrayList<Person>)
                withContext(Dispatchers.Main){
                    adapter?.notifyDataSetChanged()
                }
            }
        }

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