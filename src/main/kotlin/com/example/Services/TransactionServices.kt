package com.example.Services

import com.example.database.DBTransactionEntity
import com.example.database.DBTransactionTable
import com.example.database.DBUserTable
import com.example.entities.TransactionDraft
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.ktorm.support.mysql.insertOrUpdate

class TransactionServices {

    val kTormDatabase = Database.connect(
        url = "jdbc:mysql://localhost:3306/expapp",
        user = "root",
        password = "Word#1921"
    )

    fun getAllTransactions(): List<DBTransactionEntity>{
        return kTormDatabase.sequenceOf(DBTransactionTable).toList()
    }


    fun addTransaction(owesid: Int, draft: TransactionDraft): Boolean {

        val query = kTormDatabase.from(DBUserTable).select().where(DBUserTable.groupno eq draft.groupno)

        val num = query.totalRecords
        if (num == 0)
            return false
        if (draft.lat == 0)
            return false

        val newlat = draft.lat / num

        val updated = kTormDatabase.from(DBUserTable).select().where { DBUserTable.groupno eq draft.groupno }

        for (item in updated) {
            val userid: Int? = item[DBUserTable.userid]
            if (userid == owesid)
                continue
            kTormDatabase.insertOrUpdate(DBTransactionTable) {
                set(DBTransactionTable.usid, userid)
                set(DBTransactionTable.owesid, owesid)
                set(DBTransactionTable.lat, newlat)
                set(DBTransactionTable.total, newlat)
                onDuplicateKey {
                    set(DBTransactionTable.total, DBTransactionTable.total + newlat)
                    set(DBTransactionTable.lat, newlat)
                }
            }
        }

        return true
    }
}