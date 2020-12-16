package di

import dagger.Module
import dagger.Provides
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig
import resources.UserManagerResource
import javax.inject.Named
import javax.ws.rs.core.UriBuilder

@Module
class HttpModule {

    @Provides
    fun getResourceConfig(userManagerResource: UserManagerResource):ResourceConfig
    {
        return ResourceConfig().register(userManagerResource)

    }

    @Provides
    fun serverConfig( @Named("host") hostName:String,@Named("port") portNumber:Int,resourceConfig: ResourceConfig):HttpServer
    {
        var uri=UriBuilder.fromUri(hostName).port(portNumber).build()
        return GrizzlyHttpServerFactory.createHttpServer(uri,resourceConfig)
    }

}


//@Provides
//fun getResourceConfig(employeeResource: EmployeeResource): ResourceConfig
//{
//    return ResourceConfig().register(employeeResource)
//
//}
//
//@Provides
//fun server(@Named("hostName") host:String, @Named("port") port:Int, resourceConfig: ResourceConfig): HttpServer
//{
//    val uri= UriBuilder.fromUri(host).port(port).build()
//    return GrizzlyHttpServerFactory.createHttpServer(uri,resourceConfig)
//
//}