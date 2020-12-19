package repositories

import com.mongodb.BasicDBObject
import com.mongodb.client.MongoDatabase
import com.mongodb.util.JSON
import org.bson.Document
import org.codehaus.jackson.map.ObjectMapper
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class UserManagerRepository @Inject constructor(@Named("mongoDatabase") private val mongoDatabase: MongoDatabase, private val mapper: ObjectMapper) {


//    init {
//        val mongoCursor=mongoDatabase.getCollection("userManager").find().iterator()
//        println("MongoCursor")
//        while (mongoCursor.hasNext())
//        {
//            println("data=:${mongoCursor.next()}")
//        }
//    }

    val mongoCollection=mongoDatabase.getCollection("userManager")
    fun getUserRecordByuuid(uuid: String): User? {
        val basicDBObject = BasicDBObject()
        basicDBObject["uuid"] = uuid
        val mongoCursor = mongoCollection.find(basicDBObject).iterator()

        try {
            if (mongoCursor.hasNext()) {
                println("data")
                val doc: Document = mongoCursor.next()
                doc.remove("_id")
                val json = JSON.serialize(doc)
                return mapper.readValue(json, User::class.java)
            }
        } catch (e: Exception) {
            println("Exception are occured=:$e")
        }
        return null
    }

    fun createNewUser(record: User): User {
        try {
            val doc = Document.parse(record.toString())
            doc["uuid"] = UUID.randomUUID().toString()
            mongoCollection.insertOne(doc)
            return record
        } catch (e: Exception) {
            println("Exception are occured=:$e")
            throw Exception("${e.message} MapperException")
//            println("Exception are occured=:$e")
        }
    }

    fun updateUserData(uuid: String, record: User):String {
        println("update data on this uuid=:$uuid")
        println("data=:$record")
        val basicDBObject = BasicDBObject()
        basicDBObject["uuid"] = uuid
        val data = Document.parse(record.toString())
        val update = Document("\$set", data)
        mongoCollection.findOneAndUpdate(basicDBObject, update)
        return "Record Updated Successfully"

    }

    fun deleteUserRecordByuuid(uuid: String): String {
        try {
            val basicDBObject = BasicDBObject()
            basicDBObject["uuid"] = uuid
            mongoCollection.deleteOne(basicDBObject)
            return uuid
        } catch (e: Exception) {
            throw java.lang.Exception("${e.message}Not Deleted")
        }
    }
}

//fun main()
//{
//    var u=UserManagerRepository()
//    u.getRecordByuuid("475963sdhcasvnmfbjh36e62r33")
//}
