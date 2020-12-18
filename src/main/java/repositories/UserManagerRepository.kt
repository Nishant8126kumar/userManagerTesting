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


    fun getUserRecordByuuid(uuid: String): UserManagerModel? {
        val record = mutableListOf<UserManagerModel>()
        println("form Repository")
        val mongoCollection = mongoDatabase.getCollection("userManager")
        val basicDBObject = BasicDBObject()
        basicDBObject["uuid"] = uuid
        val mongoCursor = mongoCollection.find(basicDBObject).iterator()
        if (mongoCursor.hasNext()) {
            val doc: Document = mongoCursor.next()
            doc.remove("_id")
            val json = JSON.serialize(doc)
            val userManagerModel = mapper.readValue(json, UserManagerModel::class.java)
           return userManagerModel
        }
        return null
    }

    fun createNewUser(record: UserManagerModel): UserManagerModel {
        try {
            val doc = Document.parse(record.toString())
            doc["uuid"] = UUID.randomUUID().toString()
            mongoDatabase.getCollection("userManager").insertOne(doc)
            return record
        } catch (e: Exception) {
            throw Exception("MapperException")
//            println("Exception are occured=:$e")
        }
    }

    fun updateUserData(uuid: String, record: UserManagerModel) {
        println("update data at uuid=:$uuid")
        println("data=:$record")
        val basicDBObject = BasicDBObject()
        basicDBObject["uuid"] = uuid
        val data = Document.parse(record.toString())
        val update = Document("\$set", data)
        mongoDatabase.getCollection("userManager").findOneAndUpdate(basicDBObject, update)
    }

    fun deleteUserRecordByuuid(uuid: String): String {
        try {
            val basicDBObject = BasicDBObject()
            basicDBObject["uuid"] = uuid
            mongoDatabase.getCollection("userManager").deleteOne(basicDBObject)
            return uuid
        } catch (e: Exception) {
            throw java.lang.Exception("Not Deleted")
        }
    }
}

//fun main()
//{
//    var u=UserManagerRepository()
//    u.getRecordByuuid("475963sdhcasvnmfbjh36e62r33")
//}
