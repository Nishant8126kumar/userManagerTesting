package repositories

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import utils.EmbeddedMongoDb
import java.util.*

class UserManagerRepositoryShould {

    lateinit var classunderTest: UserManagerRepository
    private val testDataSource = TestDataSource()
    lateinit var embeddedMongoDb: EmbeddedMongoDb
    private lateinit var mongoDatabase: MongoDatabase
    private val objectMapper=ObjectMapper()
    private val uuid="0236274e-d715-44e0-9aa3-8cf45f927afb"



    @Before
    fun configure() {

        startMongoDb()
        val mongoClient = MongoClient("localhost", embeddedMongoDb.port)
        mongoDatabase = mongoClient.getDatabase("EmployeeDetails")
        classunderTest= UserManagerRepository(mongoDatabase,objectMapper)
    }

    @After
    fun closeConnection()
    {
        embeddedMongoDb.stop()
    }

    private fun startMongoDb()
    {
        val rand=Random()
        val n=rand.nextInt(99)+9900
        embeddedMongoDb = EmbeddedMongoDb(n)
        embeddedMongoDb.start()
    }

    @Test
    fun testCreateNewUser()
    {
        val user=testDataSource.getUser()
        val userFromDb = classunderTest.createNewUser(user)
        println("user From =:$userFromDb")
        Assert.assertNotNull(userFromDb)
    }

    @Test
    fun testGetUserRecordByuuid() {

        val response=classunderTest.getUserRecordByuuid(uuid)
        println("responce=:$response")
        Assert.assertNotNull(response)

    }

    @Test
    fun testDeleteUserRecordByuuid()
    {

        val response=classunderTest.deleteUserRecordByuuid(uuid)
        println("Response=:$response")
        Assert.assertNotNull(response)

    }

    @Test
    fun testUpdateUserData()
    {
        val user=testDataSource.getUser()
        val response=classunderTest.updateUserData(uuid,user)

    }















//    @Test
//    fun testCreateNewUser()
//    {
//
//    }
//
//



}