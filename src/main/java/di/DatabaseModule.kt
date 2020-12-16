package di

import com.mongodb.MongoClient
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DatabaseModule {

    @Provides
    @Named("databaseConnection")
    fun provideDatabase():MongoClient
    {
        return MongoClient("localhost",27017)
    }

}