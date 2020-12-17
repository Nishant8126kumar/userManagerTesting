package services

import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerModel
import repositories.UserManagerRepository
import javax.inject.Inject
import javax.inject.Named

class UserManagerService @Inject constructor(@Named("managerRepository") private val userManagerRepository: UserManagerRepository, private val objectMapper: ObjectMapper) {

    fun getAllUserRecord(): List<UserManagerModel> {
        return userManagerRepository.getAllUserManagerRecord()
    }

    fun getUserRecordByuuid(uuid: String): List<UserManagerModel> {

        return userManagerRepository.getUserRecordByuuid(uuid)
    }

    fun createNewUser(record: UserManagerModel): UserManagerModel {
        return userManagerRepository.createNewUser(record)
    }
    fun updateUserData(uuid: String, record: UserManagerModel) {
        println("its working update record from service layer")
        userManagerRepository.updateUserData(uuid, record)

    }

    fun deleteUserRecordByuuid(uuid: String) {
        userManagerRepository.deleteUserRecordByuuid(uuid)
    }
}