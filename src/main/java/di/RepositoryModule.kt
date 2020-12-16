package di

import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerRepository
import javax.inject.Named

@Module
class RepositoryModule {
    @Provides
    @Named("managerRepository")
    fun provideUserManagerRepositoryObj(@Named("mapper") mapper: ObjectMapper): UserManagerRepository
    {
        return UserManagerRepository(mapper)
    }
}