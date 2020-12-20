package repositories

import com.mongodb.BasicDBObject
import com.mongodb.client.MongoDatabase
import com.mongodb.util.JSON
import exceptions.MongoDbException
import org.bson.Document
import org.codehaus.jackson.map.ObjectMapper
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.jvm.Throws

class UserManagerRepository @Inject constructor(@Named("mongoDatabase") private val mongoDatabase: MongoDatabase, private val mapper: ObjectMapper) {

    lateinit var user: User

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
                user= mapper.readValue(json, User::class.java)
                return user
            }
        } catch (e: Exception) {
            println("Exception are occured=:$e")
        }
        return null
    }

    @Throws(MongoDbException::class)
    fun createNewUser(record: User): User {
        val doc = Document.parse(record.toString()) ?: throw Exception("User Record not created successfully")
         doc["uuid"] = UUID.randomUUID().toString()
         mongoCollection.insertOne(doc)
         return record
    }

    @Throws(MongoDbException::class)
    fun updateUserData(uuid: String, record: User):String {
        println("update data on this uuid=:$uuid")
        println("data=:$record")
        val basicDBObject = BasicDBObject()
        basicDBObject["uuid"] = uuid
        val doc = Document.parse(record.toString()) ?: throw MongoDbException("User Record not update successfully")
        val update = Document("\$set", doc)
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
