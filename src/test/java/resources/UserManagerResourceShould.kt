package resources

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
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

class UserManagerResourceShould : JerseyTest() {

    lateinit var userManagerService: UserManagerService
    lateinit var uuid:String
    var testDataSource = TestDataSource()

    val baseUrl = "/fretron/user-manager"
    override fun configure(): Application {

        userManagerService = Mockito.mock(UserManagerService::class.java)

        val config = ResourceConfig()
        val mapper = Mockito.mock(ObjectMapper::class.java)
        val userManagerResource = UserManagerResource(userManagerService, mapper)
        config.register(userManagerResource)
        return config.application
    }

//    @Test
//    fun return_200_after_get_all_usermanager_record() {
//
//        whenever(userManagerService.getAllUserRecord()).thenReturn(testDataSource.getDevice())
//        var responce = target("$baseUrl").request().get()
//        assert(responce.status == 200)
//        var data = responce.readEntity(String::class.java)
//        var jsonString = data.toString().replace("[", "").replace("]", "")
//        var recordUUID = JSONObject(jsonString).get("uuid")
//        assert(recordUUID != null)
//        println("data=:$recordUUID")
//    }

    @Test
    fun return_200_after_createNewUserManager() {
        val data=testDataSource.getNewUserRecord()
        whenever(userManagerService.createNewUser(data)).thenReturn(data)
        val responce=target("$baseUrl").request().post(Entity.entity(data.toString(), MediaType.APPLICATION_JSON))
//        assert(responce.status==200)
//        var createRecord=responce.readEntity(String::class.java)
//        var jsonString=createRecord.toString().replace("[","").replace("]","")

        println("data=:$responce")
    }

    @Test
    fun return_200_after_get_data_by_uuid() {
        val uuid = testDataSource.getFakeUUID()
        whenever(userManagerService.getUserRecordByuuid(any())).thenReturn(testDataSource.getDevice())
        val responce = target("$baseUrl/$uuid").request().get()
        assert(responce.status == 200)
        val data = responce.readEntity(String::class.java)
        val jsonString = data.toString().replace("[", "").replace("]", "")
        val recorUUID = JSONObject(jsonString).get("uuid")
        assert(recorUUID != null)
        println("data=:$recorUUID")
    }

    @Test
    fun return_200_after_update_data_by_uuid() {
        val updatePayload = testDataSource.updatePayLoad()
        val uuid = testDataSource.getFakeUUID()
        val responce = target("$baseUrl/update-userManager").path(uuid).request().put(Entity.entity(updatePayload.toString(), MediaType.APPLICATION_JSON))
        println("data=:$responce")
        assert(responce.status == 200)
    }

    @Test
    fun return_200_after_deleteData_by_uuid() {
        val uuid = "dc027fa7-7a28-4504-9685-9bb009bbb49d"
//        whenever(userManagerService.deleteUserRecordByuuid(uuid)).thenReturn(testDataSource.getDevice())
        val responce = target("$baseUrl/$uuid").request().delete()
        assert(responce.status == 200)
        val data = responce.readEntity(String::class.java)
        println("data=:$data")
    }
}