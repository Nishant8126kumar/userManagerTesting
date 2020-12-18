package helper

import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerModel


class TestDataSource {


    var objectMapper = ObjectMapper();
    fun getDevice(): UserManagerModel {

        var data = "{\n" +
                "  \"userName\": \"Nishant Sharma\",\n" +
                "  \"userEmail\": \"nk1761698@gmail.com\",\n" +
                "  \"userContact\": \"8126632693\",\n" +
                "  \"uuid\": \"475963sdhcasvnmfbjh36e62r33\",\n" +
                "  \"address\": \"Aligarh\",\n" +
                "  \"landMark\": \"AMU\",\n" +
                "  \"secondContact\": \"9911617346\"\n" +
                "}"
        return objectMapper.readValue(data, UserManagerModel::class.java)
    }

    fun getNewUserRecord(): UserManagerModel {
        var data = "{\n" +
                "  \"userName\": \"Nishant Sharma\",\n" +
                "  \"userEmail\": \"nk1761698@gmail.com\",\n" +
                "  \"userContact\": \"8126632693\",\n" +
                "  \"uuid\": \"475963sdhcasvnmfbjh36e62r33\",\n" +
                "  \"address\": \"Aligarh\",\n" +
                "  \"landMark\": \"AMU\",\n" +
                "  \"secondContact\": \"9911617346\"\n" +
                "}"
        return objectMapper.readValue(data, UserManagerModel::class.java)
    }

    fun updatePayLoad(): UserManagerModel {
        var data = "{\n" +
                "        \"userName\": \"Rohit Palod\",\n" +
                "        \"userEmail\": \"rohit.palod@fretron.com\",\n" +
                "        \"userContact\": \"6325639729\",\n" +
                "        \"uuid\": \"9dca9108-004a-4075-a011-838f7cd99e94\",\n" +
                "        \"address\": \"Kota\",\n" +
                "        \"landMark\": null,\n" +
                "        \"secondContact\": null\n" +
                "    }"
        return objectMapper.readValue(data, UserManagerModel::class.java)
    }

    fun getFakeUUID(): String {
//        return UUID.randomUUID().toString()
        return "841496e2-36b7-4a7a-9c90-eff88dd2560e"
    }

}
