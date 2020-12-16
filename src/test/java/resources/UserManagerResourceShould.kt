package resources

import com.nhaarman.mockito_kotlin.whenever
import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Test
import org.mockito.Mockito
import services.UserManagerService
import javax.ws.rs.core.Application

class UserManagerResourceShould: JerseyTest() {

    lateinit var userManagerService: UserManagerService
    var testDataSource=TestDataSource()

    val baseUrl = "/fretron/user-manager"
    override fun configure(): Application {

        userManagerService = Mockito.mock(UserManagerService::class.java)

        var config = ResourceConfig()
        val mapper=Mockito.mock(ObjectMapper::class.java)
        var userManagerResource = UserManagerResource(userManagerService, mapper)
        config.register(userManagerResource)
        return config.application
    }

    @Test
    fun return_200_after_get_all_usermanager_record() {

        whenever(userManagerService.getData()).thenReturn(testDataSource.getDevice())
        var responce = target("$baseUrl").request().get()
        var data=responce.readEntity(String::class.java)

        assert(responce.status==200)
    }

//    @Test
//    fun return_200_after_createNewUserManager()
//    {
//        var data=testDataSource.getNewUserRecord()
//        var responce=target(baseUrl).request().post(Entity.entity(data,MediaType.APPLICATION_JSON))
//        assert(responce.status==200)
//    }
    @Test
    fun return_200_after_get_data_by_uuid()
    {
        var uuid=testDataSource.getFakeUUID()
        whenever(userManagerService.getDataByUseruuid(uuid)).thenReturn(testDataSource.getDevice())
        var resource=target("$baseUrl/$uuid").request().get()
        println("data=:$resource")
        assert(resource.status==200)
    }
    @Test
    fun return_200_after_deleteData_by_uuid()
    {
        val uuid="dc027fa7-7a28-4504-9685-9bb009bbb49d"
//        whenever(userManagerService.deleteData(uuid)).thenReturn(testDataSource.getDevice())
        var resource=target("$baseUrl/$uuid").request().delete()
        println("Data=:$resource")
        assert(resource.status==200)
    }
}