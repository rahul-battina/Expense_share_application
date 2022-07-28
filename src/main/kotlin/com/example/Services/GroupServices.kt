package com.example.Services

import com.example.database.DBGroupTable
import com.example.entities.Groupdraft
import org.ktorm.database.Database
import org.ktorm.dsl.insertAndGenerateKey

class GroupServices {

    val kTormDatabase = Database.connect(
        url = "jdbc:mysql://localhost:3306/expapp",
        user = "root",
        password = "Word#1921"
    )

    fun addgroup(draft: Groupdraft): Boolean{

        val added = kTormDatabase.insertAndGenerateKey(DBGroupTable){
            set(DBGroupTable.gname, draft.gname)
        } as Int

        return added >0

    }
}