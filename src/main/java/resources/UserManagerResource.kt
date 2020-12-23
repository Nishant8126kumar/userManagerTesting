package resources

import org.codehaus.jackson.map.ObjectMapper
import repositories.User
import services.UserManagerService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/fretron/user-manager")
class UserManagerResource @Inject constructor(private val userManagerService: UserManagerService, private val objectMapper: ObjectMapper) {

    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getUserRecordByuuid(@PathParam("uuid") uuid: String): Response {
        val record = userManagerService.getUserRecordByuuid(uuid)
        return Response.ok(record.toString()).build()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createNewUser(request: String): Response {
        val record = objectMapper.readValue(request, User::class.java)
        val userData = userManagerService.createNewUser(record)
        println("User  data=:$userData")
        return Response.ok(record.toString()).build()
    }

    @DELETE
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteUserRecordByuuid(@PathParam("uuid") uuid: String): String {
        userManagerService.deleteUserRecordByuuid(uuid)
        return "Recorded Deleted"
    }

    @PUT
    @Path("/update-userManager/{uuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateUserRecord(@PathParam("uuid") uuid: String, request: String): String {
        val record = objectMapper.readValue(request, User::class.java)
        val response=userManagerService.updateUserData(uuid, record)
//        println("record=:"+record)
        return Response.ok(response).toString()
    }
}