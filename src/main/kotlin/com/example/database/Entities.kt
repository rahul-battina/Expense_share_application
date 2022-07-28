package com.example.database

import com.example.database.DBUserTable.bindTo
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBUserTable: Table<DBUserEntity>("usertable"){
    val userid = int("userid").primaryKey().bindTo { it.userid }
    var groupno = int("groupno").bindTo { it.groupno }
    var email = varchar("email").bindTo { it.email }
    var phno = varchar("phno").bindTo { it.phno }

}

interface DBUserEntity: Entity<DBUserEntity> {
    companion object : Entity.Factory<DBUserEntity>()
    val userid: Int
    var groupno: Int
    var email: String
    var phno: String
}

object DBTransactionTable: Table<DBTransactionEntity>("transaction"){
    var usid = int("usid").primaryKey().bindTo { it.usid }
    var owesid = int("owesid").primaryKey().bindTo { it.owesid }
    var total = int("total").bindTo { it.total }
    var lat = int("lat").bindTo { it.lat }

}

interface DBTransactionEntity: Entity<DBTransactionEntity>{
    companion object : Entity.Factory<DBTransactionEntity>()


    var usid: Int
    var owesid: Int
    var total: Int
    var lat:Int

}

object DBGroupTable: Table<DBGroupEntity>("groupname"){
    val groupno = int("groupno").primaryKey().bindTo { it.groupno }
    var gname = varchar("gname").bindTo { it.gname }

}

interface DBGroupEntity: Entity<DBGroupEntity> {
    companion object : Entity.Factory<DBGroupEntity>()
    val groupno: Int
    var gname: String

}

