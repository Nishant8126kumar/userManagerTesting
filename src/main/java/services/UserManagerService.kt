package services

import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerModel
import repositories.UserManagerRepository
import validation.Validation
import javax.inject.Inject
import javax.inject.Named

class UserManagerService @Inject constructor(@Named("managerRepository")  private val userManagerRepository: UserManagerRepository,@Named("mapper") private val objectMapper: ObjectMapper,@Named("validation") private val validation: Validation) {
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
        var valid=validation.checkDataUserValid(record)
        if (valid) {
            return userManagerRepository.creatUser(record)
        }
        else{
            println("Data not comfortable")
            return record
        }
    }

    fun deleteData(uuid: String)
    {
        userManagerRepository.deleteUseManagerData(uuid)
    }
}