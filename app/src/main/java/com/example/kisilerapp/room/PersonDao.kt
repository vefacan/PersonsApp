package com.example.kisilerapp.room

import androidx.room.*

@Dao
interface PersonDao {

    @Query("SELECT * FROM persons")
    fun getAllPerson() : List<Person>

    @Insert
    fun addPerson(person:Person)

    @Update
    fun updatePerson(person:Person)

    @Delete
    fun deletePerson(person:Person)

    @Query("SELECT * FROM persons WHERE person_name like '%' || :searchedWord || '%'")
     fun searchPerson(searchedWord:String) : List<Person>

    @Query("SELECT * FROM persons ORDER BY person_id DESC LIMIT 10")
    fun getLastTenPersons(): List<Person>

    @Query("SELECT * FROM persons WHERE person_group like '%' || :colleagues || '%'")
    fun getColleaguesPersons(colleagues:String) : List<Person>

    @Query("SELECT * FROM persons WHERE person_group like '%' || :groupPersons || '%'")
    fun getGroupPersons(groupPersons:String) : List<Person>



}