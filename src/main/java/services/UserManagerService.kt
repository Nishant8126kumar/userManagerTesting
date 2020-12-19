package services

import org.codehaus.jackson.map.ObjectMapper
import repositories.User
import repositories.UserManagerRepository
import javax.inject.Inject
import javax.inject.Named

class UserManagerService @Inject constructor(private val userManagerRepository: UserManagerRepository, private val objectMapper: ObjectMapper) {


    fun getUserRecordByuuid(uuid: String): User? {
        if (uuid.isEmpty()) {
            throw Exception("uuid not found")
        } else {
            return userManagerRepository.getUserRecordByuuid(uuid)

        }
    }

    fun createNewUser(record: User): User {
        if (record.getName() == null || record.getEmail() == null) {
            throw Exception("Require field not found")
        } else {
            return userManagerRepository.createNewUser(record)
        }
    }

    fun updateUserData(uuid: String, record: User) :String{
        println("its working update record from service layer")
        if (record.getName() == null || record.getEmail() == null) {
            throw Exception("Require field not found")
        } else {
           return userManagerRepository.updateUserData(uuid, record)
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