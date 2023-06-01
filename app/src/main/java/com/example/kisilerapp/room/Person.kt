package com.example.kisilerapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "person_id") var person_id:Int?,
                  var person_name:String?,
                  var person_surname:String?,
                  var person_phone:String?,
                  var person_adress:String?,
                  var person_group:String?) {
}
