package com.example.repository

import com.example.database.DBUserTable
import com.example.database.DatabaseManager
import com.example.entities.*

class MySQLRepository: Functions {
    private val database = DatabaseManager()

    override fun getAllGroups(): List<User> {
        return database.getAllGroups().
        map { User(it.userid, it.groupno, it.email, it.phno) }
    }




    override fun getAllTransactions(): List<Transaction> {
        return database.getAllTransactions().
        map { Transaction( it.usid, it.owesid, it.total, it.lat) }
    }





    /*
    override fun getGroup(groupno: Int): List<User>? {
        return database.getGroup(groupno)
            ?.map { User(it.userid, it.groupno, it.email, it.phno) }


    }

     */



    /*
    override fun getTransactions(usid: Int): List<Transaction>? {
        return database.getTransactions(usid)
            ?.map { Transaction(it.tranid, it.usid, it.owesid, it.total, it.lat) }
    }

     */


    override fun addTransaction(owesto: Int, draft: TransactionDraft): Boolean {
        return database.addTransaction(owesto, draft)
    }

    override fun addgroup(draft: Groupdraft): Boolean {
        return database.addgroup(draft)
    }

    override fun addUser(draft: UserDraft): Boolean {
        return database.addUser(draft)
    }
}