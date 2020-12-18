package resources

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import component.DaggerTestComponent
import component.TestComponent
import component.UserComponent
import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jettison.json.JSONObject
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Test
import org.mockito.Mockito
import services.UserManagerService
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType

class UserManagerResourceShould: JerseyTest() {

    lateinit var userManagerService: UserManagerService
    lateinit var testComponent: TestComponent
    lateinit var uuid: String
    var testDataSource = TestDataSource()

    val baseUrl = "/fretron/user-manager"
    override fun configure(): Application {

        userManagerService = Mockito.mock(UserManagerService::class.java)

        val config = ResourceConfig()
        val mapper = ObjectMapper()

        testComponent=DaggerTestComponent.builder().build()

        val userManagerResource = UserManagerResource(testComponent.userManagerService(), testComponent.mapper())
        config.register(userManagerResource)
        return config.application

    }

    @Test
    fun return_200_after_createNewUserManager() {

        val data = testDataSource.getNewUserRecord()
        whenever(userManagerService.createNewUser(data)).thenReturn(data)
        val response = target("$baseUrl").request().post(Entity.entity(data.toString(), MediaType.APPLICATION_JSON))
        assert(response.status == 200)
        var record = response.readEntity(String::class.java)
        var json = JSONObject(record).get("userName")
        uuid = JSONObject(record).get("uuid").toString()
        println("uuid=:" + uuid)
//        println("data=:$json")

    }

//    @Test
//    fun return_200_after_get_data_by_uuid() {
//        return_200_after_createNewUserManager()
//
//        whenever(userManagerService.getUserRecordByuuid(uuid)).thenReturn(testDataSource.getDevice())
//        val response = target("$baseUrl/$uuid").request().get()
//        println("response=:"+response)
//        assert(response.status == 200)
//        val data = response.readEntity(String::class.java)
//        println("data=:$data")
//        uuid=JSONObject(data).get("uuid").toString()
//        println("uuid=:$uuid")
//
//    }

    @Test
    fun return_200_after_update_data_by_uuid() {

        return_200_after_createNewUserManager()
        val updatePayload = testDataSource.updatePayLoad()
        val responce = target("$baseUrl/update-userManager").path(uuid).request().put(Entity.entity(updatePayload.toString(), MediaType.APPLICATION_JSON))
        assert(responce.status == 200)
        var data = responce.readEntity(String::class.java)
        println("data=:" + data)

    }

    @Test
    fun return_200_after_deleteData_by_uuid() {

        return_200_after_createNewUserManager()
        whenever(userManagerService.deleteUserRecordByuuid(any())).thenReturn(uuid)
        val responce = target("$baseUrl/$uuid").request().delete()
        assert(responce.status == 200)
        val data = responce.readEntity(String::class.java)
        println("data ok=:$data")

    }
}