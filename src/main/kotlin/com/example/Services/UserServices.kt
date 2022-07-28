package com.example.Services

import com.example.database.DBGroupTable
import com.example.database.DBUserEntity
import com.example.database.DBUserTable
import com.example.entities.UserDraft
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class UserServices {
    val kTormDatabase = Database.connect(
        url = "jdbc:mysql://localhost:3306/expapp",
        user = "root",
        password = "Word#1921"
    )

    fun getAllUsers():List<DBUserEntity>{
        return kTormDatabase.sequenceOf(DBUserTable).toList()
    }

    fun addUser(draft: UserDraft): Boolean{

        val exists = kTormDatabase.sequenceOf(DBGroupTable).firstOrNull{it.groupno eq DBUserTable.groupno }
        if (exists == null)
            return false

        val added = kTormDatabase.insertAndGenerateKey(DBUserTable){
            set(DBUserTable.groupno, draft.groupno)
            set(DBUserTable.email, draft.email)
            set(DBUserTable.phno, draft.phno)
        }as Int

        return added > 0
    }
}