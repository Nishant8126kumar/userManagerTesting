package component

import dagger.Component
import di.*
import org.glassfish.grizzly.http.server.HttpServer


@Component(modules = [ConfigModule::class, HttpModule::class, ServiceModule::class, RepositoryModule::class, MapperProvide::class, DatabaseModule::class])
interface UserComponent {
    fun server(): HttpServer
//    fun userService():UserManagerService
}