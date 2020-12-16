package repositories

import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import com.mongodb.util.JSON
import dagger.Module
import dagger.Provides
import org.bson.Document
import org.codehaus.jackson.map.ObjectMapper
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class UserManagerRepository @Inject constructor(@Named("mongoDatabase") private val mongoDatabase: MongoDatabase,@Named("mapper") private  val mapper: ObjectMapper) {


    fun getAllUserManagerRecord():List<UserManagerModel>
    {
        var recod= mutableListOf<UserManagerModel>()
        println("form Repository")
        var mongoCollection= mongoDatabase.getCollection("userManager")
        var mongoCursor=mongoCollection.find().iterator()
        while (mongoCursor.hasNext())
        {
            try {
                var doc:Document=mongoCursor.next()
                doc.remove("_id")
                var json=JSON.serialize(doc)
                var userManagerModel=mapper.readValue(json,UserManagerModel::class.java)
                recod.add(userManagerModel)
            } catch (e: Exception) {
                println("Exception are occured=:"+e)

            }
        }
        println("data=:$recod")
        return recod
    }

    fun getRecordByuuid(uuid:String):List<UserManagerModel>
    {
        var recod= mutableListOf<UserManagerModel>()
        println("form Repository")
        val mongoCollection= mongoDatabase.getCollection("userManager")
        val basicDBObject=BasicDBObject()
        basicDBObject["uuid"] = uuid
        var mongoCursor=mongoCollection.find(basicDBObject).iterator()
        while (mongoCursor.hasNext())
        {
            var doc:Document=mongoCursor.next()
            doc.remove("_id")
            var json=JSON.serialize(doc)
            var userManagerModel=mapper.readValue(json,UserManagerModel::class.java)
            recod.add(userManagerModel)
        }
        println("data=:$recod")
        return recod
    }

    fun creatUser(record: UserManagerModel):UserManagerModel
    {
        try {
            var doc=Document.parse(record.toString())
            doc["uuid"] = UUID.randomUUID().toString()
            val mongoCollection=mongoDatabase.getCollection("userManager").insertOne(doc)

            return record
        } catch (e: Exception) {
            throw Exception("MapperException")
//            println("Exception are occured=:$e")
        }
    }
    fun deleteUseManagerData(uuid: String):String
    {
        try {
            val basicDBObject=BasicDBObject()
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
