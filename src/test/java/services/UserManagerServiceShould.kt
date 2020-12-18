package services

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import repositories.UserManagerRepository
import java.util.*

class UserManagerServiceShould {

    lateinit var classUnderTest: UserManagerService
    private val mapper = Mockito.mock(ObjectMapper::class.java)
    private val userRepository = Mockito.mock(UserManagerRepository::class.java)
    private val testDataSource = TestDataSource()
    lateinit var uuid: String

    @Before
    fun setup() {
        classUnderTest = UserManagerService(userRepository, mapper)
    }
    @Test
    fun testCreateNewUser() {
        var userData = testDataSource.getNewUserRecord()
        whenever(userRepository.createNewUser(any())).thenReturn(userData)
        var request = classUnderTest.createNewUser(userData)
        Assert.assertNotNull(request)
        println("data=:$request")
        uuid=request.getUuid().toString()
        println("uuid=:$uuid")
        verify(userRepository).createNewUser(userData)
    }

    @Test
    fun testGetDatabyUUID() {
       testCreateNewUser()
        whenever(userRepository.getUserRecordByuuid(uuid)).thenReturn(testDataSource.getDevice())
        var request = classUnderTest.getUserRecordByuuid(uuid)
        println("Test get data by uuid=:$request")
        Assert.assertNotNull(request)
        verify(userRepository).getUserRecordByuuid(uuid)
    }



    @Test
    fun testDeleteUserByUUUID() {
        testCreateNewUser()
        println("Record=:$uuid")
        whenever(userRepository.deleteUserRecordByuuid(uuid)).thenReturn(testDataSource.getFakeUUID())
        var request = classUnderTest.deleteUserRecordByuuid(uuid)
        println("Data=:$request")
        Assert.assertNotNull(request)
        verify(userRepository).deleteUserRecordByuuid(uuid)
    }


}