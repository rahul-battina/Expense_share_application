package com.example.repository

import com.example.entities.*

interface Functions {

    fun getAllGroups(): List<User>                      ////done

    //fun getGroup(groupno:Int): List<User>?


    fun getAllTransactions(): List<Transaction>         ///done

    //fun getTransactions(usid: Int): List<Transaction>?

    fun addUser(draft: UserDraft): Boolean              ////done

    fun addTransaction(owesid: Int,draft: TransactionDraft): Boolean // done

    fun addgroup(draft: Groupdraft): Boolean
            //////Done

}