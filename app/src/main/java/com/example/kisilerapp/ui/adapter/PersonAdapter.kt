package com.example.kisilerapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kisilerapp.R
import com.example.kisilerapp.room.Person


class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private var personList: ArrayList<Person> = ArrayList()
    private var onClickItem: ((Person) -> Unit)? = null

    fun addItems(items: ArrayList<Person>) {
        this.personList = items
    }

    fun setOnClickItem(callback: ((Person) -> Unit)) {
        this.onClickItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonViewHolder(

        LayoutInflater.from(parent.context).inflate(R.layout.custom_recyclerview, parent, false)
    )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.bindView(person)
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(person)
        }
    }


    override fun getItemCount(): Int {
        return personList.size
    }


    class PersonViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private var name = view.findViewById<TextView>(R.id.txtName)
        private var surname = view.findViewById<TextView>(R.id.txtSurname)
        private var group = view.findViewById<TextView>(R.id.txtGroup)
        private var telephone = view.findViewById<TextView>(R.id.txtTelephone)
        private var adress = view.findViewById<TextView>(R.id.txtAdress)

        fun bindView(person: Person) {
            name.text = person.person_name
            surname.text = person.person_surname
            group.text = person.person_group
            telephone.text = person.person_phone
            adress.text = person.person_adress
        }

    }


}