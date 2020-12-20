package exceptions

open class UserException:Exception {
    constructor():super(){}
    constructor(msg:String):super(msg){}
    constructor(msg: String?,e:Exception){}
}