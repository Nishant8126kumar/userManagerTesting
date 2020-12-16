package validation

import repositories.UserManagerModel

class Validation {

    fun checkDataUserValid(record: UserManagerModel):Boolean
    {

       return if(record!=null)true else false
    }
}