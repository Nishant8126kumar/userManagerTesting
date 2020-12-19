package helper

import org.codehaus.jackson.map.ObjectMapper
import repositories.User


class TestDataSource {


    private val objectMapper = ObjectMapper();
    fun getUser(): User {
        val data = "{\n" +
                "  \"name\": \"Shivam Sharma\",\n" +
                "  \"email\": \"shivamSharma1562@gmail.com\",\n" +
                "  \"contact\": \"8126632693\",\n" +
                "  \"uuid\": \"475963sdhcasvnmfbjh36e62r33\",\n" +
                "  \"address\": \"Aligarh\",\n" +
                "  \"landMark\": \"AMU\",\n" +
                "  \"secondContact\": \"9911617346\"\n" +
                "}"
        return objectMapper.readValue(data, User::class.java)
    }

    fun getNewUserRecord(): User {
        val data = "{\n" +
                "  \"name\": \"Nishant Sharma\",\n" +
                "  \"email\": \"nk1761698@gmail.com\",\n" +
                "  \"contact\": \"8126632693\",\n" +
                "  \"uuid\": \"475963sdhcasvnmfbjh36e62r33\",\n" +
                "  \"address\": \"Aligarh\",\n" +
                "  \"landMark\": \"AMU\",\n" +
                "  \"secondContact\": \"9911617346\"\n" +
                "}"
        return objectMapper.readValue(data, User::class.java)
    }

    fun updatePayLoad(): User {
        val data = "{\n" +
                "        \"name\": \"Rohit Palod\",\n" +
                "        \"email\": \"rohit.palod@fretron.com\",\n" +
                "        \"contact\": \"6325639729\",\n" +
                "        \"uuid\": \"9dca9108-004a-4075-a011-838f7cd99e94\",\n" +
                "        \"address\": \"Kota\",\n" +
                "        \"landMark\": null,\n" +
                "        \"secondContact\": null\n" +
                "    }"
        return objectMapper.readValue(data, User::class.java)
    }
}
