package helper

import com.mongodb.util.JSON
import org.bson.Document
import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerModel
import java.util.*


class TestDataSource {


    var objectMapper=ObjectMapper();
    fun getDevice():List<UserManagerModel> {

        var data="{\n" +
                "  \"userName\": \"Nishant Sharma\",\n" +
                "  \"userEmail\": \"nk1761698@gmail.com\",\n" +
                "  \"userContact\": \"8126632693\",\n" +
                "  \"uuid\": \"475963sdhcasvnmfbjh36e62r33\",\n" +
                "  \"address\": \"Aligarh\",\n" +
                "  \"landmark\": \"AMU\",\n" +
                "  \"secondContact\": \"9911617346\"\n" +
                "}"
        var list= mutableListOf<UserManagerModel>()
        var doc=Document()
        doc["userName"] = "Nishant"
        doc["userEmail"]="nkSharma1761698@gmail.com"
        doc["userContact"]="8126632693"
        doc["uuid"]=UUID.randomUUID().toString()

        var jsonString= JSON.serialize(doc)
        var employees=objectMapper.readValue(jsonString,UserManagerModel::class.java)
        list.add(employees)
        return list
    }
    fun getNewUserRecord():String
    {
        var data="{\n" +
                "  \"userName\": \"Nishant Sharma\",\n" +
                "  \"userEmail\": \"nk1761698@gmail.com\",\n" +
                "  \"userContact\": \"8126632693\",\n" +
                "  \"uuid\": \"475963sdhcasvnmfbjh36e62r33\",\n" +
                "  \"address\": \"Aligarh\",\n" +
                "  \"landMark\": \"AMU\",\n" +
                "  \"secondContact\": \"9911617346\"\n" +
                "}"
        return data
    }
    fun getFakeUUID():String
    {
//        return UUID.randomUUID().toString()
        return "841496e2-36b7-4a7a-9c90-eff88dd2560e"
    }

}
