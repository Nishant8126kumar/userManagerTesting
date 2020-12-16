package services
import com.nhaarman.mockito_kotlin.whenever
import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import repositories.UserManagerModel
import repositories.UserManagerRepository
import validation.Validation

class UserManagerServiceShould
{

    lateinit var classUnderTest:UserManagerService
    private val mapper=Mockito.mock(ObjectMapper::class.java)
    private val userRepository=Mockito.mock(UserManagerRepository::class.java)
    private val testDataSource=TestDataSource()
    private val validation=Mockito.mock(Validation::class.java)
    @Before
    fun setup()
    {
        classUnderTest= UserManagerService(userRepository,mapper,validation)
    }

    @Test
    fun getDataTest()
    {

        whenever(userRepository.getAllUserManagerRecord()).thenReturn(testDataSource.getDevice())
        var request=classUnderTest.getData()
        println("Data=:$request")
        assert(request!=null)

    }

    @Test
    fun testGetDatabyUUID()
    {
        val uuid=testDataSource.getFakeUUID()
        whenever(userRepository.getRecordByuuid(uuid)).thenReturn(testDataSource.getDevice())
        var request=classUnderTest.getDataByUseruuid(uuid)
        println("Test get data by uuid=:$request")
        assert(request!=null)
    }

    @Test
    fun testCreateNewUser()
    {
        var data=testDataSource.getNewUserRecord()
        val objectMapper=ObjectMapper()
        var record=objectMapper.readValue(data,UserManagerModel::class.java)

        whenever(userRepository.creatUser(record)).thenReturn(record)
        var request=classUnderTest.createNewUserManager(record)

//        println("data=:$record")

        assert(request!=null)
    }
    @Test
    fun testDeleteUserByUUUID()
    {
        var record=testDataSource.getFakeUUID()
        println("Record=:$record")

        whenever(userRepository.deleteUseManagerData(record)).thenReturn(testDataSource.getFakeUUID())
        var request=classUnderTest.deleteData(record)
        println("Data=:$request")
        assert(request!=null)
    }
//    @After
//    fun destroy()
//    {
//
//    }

}