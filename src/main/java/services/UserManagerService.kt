package services

import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerModel
import repositories.UserManagerRepository
import javax.inject.Inject
import javax.inject.Named

class UserManagerService @Inject constructor(@Named("managerRepository") private val userManagerRepository: UserManagerRepository, private val objectMapper: ObjectMapper) {


    fun getUserRecordByuuid(uuid: String): UserManagerModel? {
        if (uuid.isEmpty()) {
            throw Exception("uuid not found")
        } else {
            return userManagerRepository.getUserRecordByuuid(uuid)

        }
    }

    fun createNewUser(record: UserManagerModel): UserManagerModel {
        if (record.getUserName() == null || record.getUserEmail() == null) {
            throw Exception("Require field not found")
        } else {
            return userManagerRepository.createNewUser(record)
        }
    }

    fun updateUserData(uuid: String, record: UserManagerModel) {
        println("its working update record from service layer")
        if (record.getUserName() == null || record.getUserEmail() == null) {
            throw Exception("Require field not found")
        } else {
            userManagerRepository.updateUserData(uuid, record)
        }
    }

    fun deleteUserRecordByuuid(uuid: String): String {
        if (uuid.isEmpty()) {
            throw Exception("uuid require")
        } else {
            return userManagerRepository.deleteUserRecordByuuid(uuid)
        }
    }
}