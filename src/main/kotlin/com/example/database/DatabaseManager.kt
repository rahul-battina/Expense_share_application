package com.example.database

import com.example.Services.GroupServices
import com.example.Services.TransactionServices
import com.example.Services.UserServices
import com.example.database.DBTransactionTable.owesid
import com.example.database.DBUserTable.groupno
import com.example.database.DBUserTable.userid
import com.example.entities.*
import org.ktorm.database.Database
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.ktorm.dsl.*
import org.ktorm.support.mysql.insertOrUpdate
import com.example.Services.UserServices.*

class DatabaseManager {



    val userServices = UserServices()
    val transactionServices = TransactionServices()
    val groupServices = GroupServices()


    fun getAllGroups(): List<User>{
        return userServices.getAllUsers().
        map { User(it.userid, it.groupno, it.email, it.phno) }
    }

    fun getAllTransactions(): List<Transaction>{
        return transactionServices.getAllTransactions().
        map { Transaction( it.usid, it.owesid, it.total, it.lat) }
    }

    fun addUser(draft: UserDraft):Boolean{
        return userServices.addUser(draft)

    }

    fun addTransaction(owesto: Int, draft: TransactionDraft):Boolean{
        return transactionServices.addTransaction(owesto, draft)
    }
    fun addgroup(draft:Groupdraft):Boolean{
        return groupServices.addgroup(draft)
    }

    /*
    fun getGroup(groupno:Int): List<DBUserEntity>?{

        val req = kTormDatabase.from(DBUserTable).select().
        where { DBUserTable.groupno eq groupno }




    }

     */



//    fun getAllTransactions(): List<DBTransactionEntity>{
//        return kTormDatabase.sequenceOf(DBTransactionTable).toList()
//    }





    /*
    fun getTransactions(usid: Int): List<DBTransactionEntity>?{
        return kTormDatabase.sequenceOf(DBTransactionTable)?.toList()

    }

     */

//    fun addUser(draft: UserDraft): Boolean{
//
//        val exists = kTormDatabase.sequenceOf(DBGroupTable).firstOrNull{it.groupno eq groupno}
//        if (exists == null)
//            return false
//
//        val added = kTormDatabase.insertAndGenerateKey(DBUserTable){
//            set(DBUserTable.groupno, draft.groupno)
//            set(DBUserTable.email, draft.email)
//            set(DBUserTable.phno, draft.phno)
//        }as Int
//
//        return added > 0
//    }


//    fun addUser(draft: UserDraft){
//        return userServices.addUser()
//    }
//
//    fun addTransaction(owesid: Int, draft: TransactionDraft): Boolean {
//
//        val query = kTormDatabase.from(DBUserTable).select().where(DBUserTable.groupno eq draft.groupno)
//
//        val num = query.totalRecords
//        if (num == 0)
//            return false
//        if (draft.lat == 0)
//            return false
//
//        val newlat = draft.lat / num
//
//        val updated = kTormDatabase.from(DBUserTable).select().where { DBUserTable.groupno eq draft.groupno }
//
//        for (item in updated) {
//            val userid: Int? = item[DBUserTable.userid]
//            if (userid == owesid)
//                continue
//            kTormDatabase.insertOrUpdate(DBTransactionTable) {
//                set(DBTransactionTable.usid, userid)
//                set(DBTransactionTable.owesid, owesid)
//                set(DBTransactionTable.lat, newlat)
//                set(DBTransactionTable.total, newlat)
//                onDuplicateKey {
//                    set(DBTransactionTable.total, DBTransactionTable.total + newlat)
//                    set(DBTransactionTable.lat, newlat)
//                }
//            }
//        }
//
//        return true
//    }


        /*
        var num = 4
        var res = draft.lat/num
        var sum = DBTransactionTable.total + (draft.lat)/num

        val updated = kTormDatabase.update(DBTransactionTable){
            set(DBTransactionTable.lat, res )
            set(DBTransactionTable.total,sum)
            where {
                it.owesid eq owesid
            }
        }
        return updated > 0

         */



//    fun addgroup(draft: Groupdraft): Boolean{
//
//        val added = kTormDatabase.insertAndGenerateKey(DBGroupTable){
//            set(DBGroupTable.gname, draft.gname)
//        } as Int
//
//        return added >0
//
//    }
}