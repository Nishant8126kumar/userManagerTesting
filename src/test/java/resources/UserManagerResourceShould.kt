package resources

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import component.DaggerTestComponent
import component.TestComponent
import helper.TestDataSource
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

        val config = ResourceConfig()
        testComponent=DaggerTestComponent.builder().build()
        val userManagerResource = UserManagerResource(testComponent.userManagerService(), testComponent.mapper())
        config.register(userManagerResource)
        return config.application

    }

    @Test
    fun testGetUserRecordByuuid()
    {
        uuid="d7a972a2-bba8-445b-a58e-65c0ed1b3754"
        var response=target("$baseUrl/$uuid").request().get()
        assert(response.status==200)
    }

    @Test
    fun return_200_after_createNewUserManager() {
        val user = testDataSource.getNewUserRecord()
        val response = target("$baseUrl").request().post(Entity.entity(user.toString(), MediaType.APPLICATION_JSON))
        assert(response.status == 200)
        println("respo=:$response")
        val record = response.readEntity(String::class.java)
        uuid = JSONObject(record).get("uuid").toString()
        println("uuid=:$uuid")

    }

    @Test
    fun return_200_after_update_data_by_uuid() {

        return_200_after_createNewUserManager()
        val user = testDataSource.updatePayLoad()
        val response = target("$baseUrl/update-userManager").path(uuid).request().put(Entity.entity(user.toString(), MediaType.APPLICATION_JSON))
        assert(response.status == 200)
        val data = response.readEntity(String::class.java)

    }

    @Test
    fun return_200_after_deleteData_by_uuid() {

        return_200_after_createNewUserManager()
        val responce = target("$baseUrl/$uuid").request().delete()
        assert(responce.status == 200)
        val data = responce.readEntity(String::class.java)
        println("data ok=:$data")

    }
}