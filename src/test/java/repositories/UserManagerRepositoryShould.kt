package repositories

import com.mongodb.client.MongoDatabase
import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UserManagerRepositoryShould {

    lateinit var userManagerRepository:UserManagerRepository
    private val mapper=Mockito.mock(ObjectMapper::class.java)
//    private lateinit var embeddedMongoDb: EmbeddedMongoDb
//    private lateinit var database: MongoDatabase
    var mongoDatabase=Mockito.mock(MongoDatabase::class.java)
    private val testDataSource=TestDataSource()
    @Before
    fun setUp()
    {
        userManagerRepository=UserManagerRepository(mongoDatabase,mapper)
    }

    @Test
    fun testGetAllUserManagerRecord()
    {
//        var request=userManagerRepository.getAllUserManagerRecord()

//        assert(request!=null)
    }
}