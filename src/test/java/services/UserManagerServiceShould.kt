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
    private val uuid=UUID.randomUUID().toString()
    @Before
    fun setup() {
        classUnderTest = UserManagerService(userRepository, mapper)
    }
    @Test
    fun testCreateNewUser() {
        val userData = testDataSource.getNewUserRecord()
        whenever(userRepository.createNewUser(any())).thenReturn(userData)
        val request = classUnderTest.createNewUser(userData)
        Assert.assertNotNull(request)
        println("data=:$request")
        println("uuid=:$uuid")
        verify(userRepository).createNewUser(userData)
    }

    @Test
    fun testGetDatabyUUID() {
       testCreateNewUser()
        val user=testDataSource.getUser()
        whenever(userRepository.getUserRecordByuuid(any())).thenReturn(user)
        val request = classUnderTest.getUserRecordByuuid(uuid)
        println("Test get data by uuid=:$request")
        Assert.assertNotNull(request)
        verify(userRepository).getUserRecordByuuid(uuid)
    }

    @Test
    fun testDeleteUserByUUUID() {
        testCreateNewUser()
        println("Record=:$uuid")
        whenever(userRepository.deleteUserRecordByuuid(uuid)).thenReturn(uuid)
        val request = classUnderTest.deleteUserRecordByuuid(uuid)
        println("Data=:$request")
        Assert.assertNotNull(request)
        verify(userRepository).deleteUserRecordByuuid(uuid)
    }

    @Test
    fun testUpdateUserData()
    {
        val user=testDataSource.getUser()
        whenever(userRepository.updateUserData(any(), any())).thenReturn("Record Updated")
        val request=classUnderTest.updateUserData(uuid,user)
        println("request=:$request")
        Assert.assertNotNull(request)
        verify(userRepository).updateUserData(uuid, user)
    }
}