package di

import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerRepository
import services.UserManagerService
import javax.inject.Named

@Module
class ServiceModule {

    @Provides
    @Named("userServiceObj")
    fun provideUserServiceObj(@Named("managerRepository") userManagerRepository: UserManagerRepository,@Named("mapper") mapper:ObjectMapper):UserManagerService
    {
        return UserManagerService(userManagerRepository,mapper)
    }
}