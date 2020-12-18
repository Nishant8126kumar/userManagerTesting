import component.DaggerUserComponent
import component.UserComponent


fun main() {
    val userComponent: UserComponent = DaggerUserComponent.builder().build()
    userComponent.server()
}