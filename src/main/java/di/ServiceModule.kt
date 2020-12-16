package di

import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerRepository
import services.UserManagerService
import validation.Validation
import javax.inject.Named

@Module
class ServiceModule {

    @Provides
    @Named("userServiceObj")
    fun provideUserServiceObj(@Named("managerRepository") userManagerRepository: UserManagerRepository,@Named("mapper") mapper:ObjectMapper,@Named("validation") validation: Validation):UserManagerService
    {
        return UserManagerService(userManagerRepository,mapper,validation)
    }
}