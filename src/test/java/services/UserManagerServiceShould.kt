package services

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import repositories.UserManagerRepository

class UserManagerServiceShould {

    lateinit var classUnderTest: UserManagerService
    private val mapper = Mockito.mock(ObjectMapper::class.java)
    private val userRepository = Mockito.mock(UserManagerRepository::class.java)
    private val testDataSource = TestDataSource()

    @Before
    fun setup() {
        classUnderTest = UserManagerService(userRepository, mapper)
    }

    @Test
    fun getDataTest() {

        whenever(userRepository.getAllUserManagerRecord()).thenReturn(testDataSource.getDevice())
        var request = classUnderTest.getAllUserRecord()
        println("Data=:$request")
        assert(request != null)
        verify(userRepository).getAllUserManagerRecord()
    }

    @Test
    fun testGetDatabyUUID() {
        val uuid = testDataSource.getFakeUUID()
        whenever(userRepository.getUserRecordByuuid(uuid)).thenReturn(testDataSource.getDevice())
        var request = classUnderTest.getUserRecordByuuid(uuid)
        println("Test get data by uuid=:$request")
        assert(request != null)
        verify(userRepository).getUserRecordByuuid(uuid)
    }

    @Test
    fun testCreateNewUser() {
        var data = testDataSource.getNewUserRecord()
        whenever(userRepository.createNewUser(data)).thenReturn(data)
        var request = classUnderTest.createNewUser(data)
        assert(request != null)
        println("data=:$request")
        verify(userRepository).createNewUser(data)

//        assert(request!=null)
    }

    @Test
    fun testDeleteUserByUUUID() {
        var uuid = testDataSource.getFakeUUID()
        println("Record=:$uuid")

        whenever(userRepository.deleteUserRecordByuuid(uuid)).thenReturn(testDataSource.getFakeUUID())
        var request = classUnderTest.deleteUserRecordByuuid(uuid)
        println("Data=:$request")
        assert(request != null)
        verify(userRepository).deleteUserRecordByuuid(uuid)
    }
//    @After
//    fun destroy()
//    {
//
//    }

}