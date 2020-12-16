package resources

import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerModel
import services.UserManagerService
import javax.inject.Inject
import javax.inject.Named
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/fretron/user-manager")
class UserManagerResource @Inject constructor(@Named("userServiceObj") val userManagerService: UserManagerService,@Named("mapper") val objectMapper: ObjectMapper)
{
    @GET
    @Produces(MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN)
    fun getAllRecord():Response {

        var record = userManagerService.getData()
        return Response.ok(record.toString()).build()

    }
    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN)
    fun getRecordByUuid(@PathParam("uuid") uuid:String):Response
    {

        var record=userManagerService.getDataByUseruuid(uuid)
        return Response.ok(record.toString()).build()

    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN)
    fun createNewUserManager(request:String):String
    {
        var record=objectMapper.readValue(request,UserManagerModel::class.java)
        var userData=userManagerService.createNewUserManager(record)
//        userManagerService
        return Response.ok(userData).build().toString()

    }

    @DELETE
    @Path("/{uuid}")
    fun deleteRecord(@PathParam("uuid") uuid:String):String
    {
        userManagerService.deleteData(uuid)
        return "Recorded Deleted"
    }
}