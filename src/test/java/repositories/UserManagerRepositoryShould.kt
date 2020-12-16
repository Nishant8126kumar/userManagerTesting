package repositories

import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UserManagerRepositoryShould {

    lateinit var userManagerRepository:UserManagerRepository
    private val mapper=Mockito.mock(ObjectMapper::class.java)
    private val testDataSource=TestDataSource()
    @Before
    fun setUp()
    {
        userManagerRepository=UserManagerRepository(mapper)
    }

    @Test
    fun testGetAllUserManagerRecord()
    {
        var request=userManagerRepository.getAllUserManagerRecord()

        assert(request!=null)
    }
}