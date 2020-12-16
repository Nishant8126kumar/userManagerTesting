package services

import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerModel
import repositories.UserManagerRepository
import javax.inject.Inject
import javax.inject.Named

class UserManagerService @Inject constructor(@Named("managerRepository")  private val userManagerRepository: UserManagerRepository,@Named("mapper") private val objectMapper: ObjectMapper) {
    fun getData():List<UserManagerModel>
    {

     return  userManagerRepository.getAllUserManagerRecord()
        println("Fron userManagerService side")
//        return "ok it working"
    }
    fun getDataByUseruuid(uuid:String):List<UserManagerModel>
    {
        return userManagerRepository.getRecordByuuid(uuid)
    }

    fun createNewUserManager(record: UserManagerModel):UserManagerModel
    {
        return userManagerRepository.creatUser(record)
    }

    fun deleteData(uuid: String)
    {
        userManagerRepository.deleteUseManagerData(uuid)
    }
}