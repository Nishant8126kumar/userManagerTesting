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
    private val uuid="07b06f91-284f-42ff-b33b-48c129fc7b3d"



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
        val userFromDb: User = classunderTest.createNewUser(user)
        println("user From =:$userFromDb")
        Assert.assertNotNull(userFromDb.getName())
        Assert.assertNotNull(user.getEmail())
        Assert.assertNotNull(user.getContact())
    }

    @Test
    fun testGetUserRecordByuuid() {

        val response=classunderTest.getUserRecordByuuid(uuid)
        println("responce=:$response")
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
        classunderTest.updateUserData(uuid,user)
        Assert.assertNotNull(user.getName())
        Assert.assertNotNull((user.getEmail()))
        Assert.assertNotNull(user.getContact())
    }















//    @Test
//    fun testCreateNewUser()
//    {
//
//    }
//
//



}