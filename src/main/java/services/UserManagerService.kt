package services

import exceptions.UserException
import org.codehaus.jackson.map.ObjectMapper
import repositories.User
import repositories.UserManagerRepository
import javax.inject.Inject

class UserManagerService @Inject constructor(private val userManagerRepository: UserManagerRepository, private val objectMapper: ObjectMapper) {


    @Throws(UserException::class)
    fun getUserRecordByuuid(uuid: String): User? {
        if (uuid.isEmpty()) {
            throw throw UserException("uuid not found exception $uuid")
        } else {
            return userManagerRepository.getUserRecordByuuid(uuid)
        }
    }

    @Throws(UserException::class)
    fun createNewUser(record: User): User {
        if (record.getName() == null || record.getEmail() == null) {
            throw UserException("Required Field not found")
        } else {
            return userManagerRepository.createNewUser(record)
        }
    }

    @Throws(UserException::class)
    fun updateUserData(uuid: String, record: User) :String{
        println("its working update record from service layer")
        if (record.getName() == null || record.getEmail() == null) {
            throw UserException("Required Field not found in Update Payload")
        } else {
           return userManagerRepository.updateUserData(uuid, record)
        }
    }

    @Throws(UserException::class)
    fun deleteUserRecordByuuid(uuid: String): String {
        if (uuid.isEmpty()) {
            throw UserException("uuid id not found for search user data")
        } else {
            return userManagerRepository.deleteUserRecordByuuid(uuid)
        }
    }
}