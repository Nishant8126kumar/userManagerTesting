package component

import Di.UserManagerResourceTest
import dagger.Component
import di.DatabaseModule
import di.MapperProvide
import di.RepositoryModule
import di.ServiceModule
import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerRepository
import services.UserManagerService

@Component(modules = [UserManagerResourceTest::class,ServiceModule::class,RepositoryModule::class,MapperProvide::class,DatabaseModule::class])
interface TestComponent {
    fun userManagerResourceTest():UserManagerResourceTest
    fun  userManagerService():UserManagerService
    fun userManagerRepository():UserManagerRepository
    fun mapper():ObjectMapper
}