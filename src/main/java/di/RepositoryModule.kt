package di

import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import repositories.UserManagerRepository
import javax.inject.Named

@Module
class RepositoryModule {
    @Provides
    @Named("managerRepository")
    fun provideUserManagerRepositoryObj(@Named("mapper") mapper: ObjectMapper,@Named("mongoDatabase") mongoDatabase: MongoDatabase): UserManagerRepository
    {
        return UserManagerRepository(mongoDatabase,mapper)
    }
}