package di

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DatabaseModule {

    @Provides
    @Named("databaseClient")
    fun provideClient(): MongoClient {
        return MongoClient("localhost", 27017)
    }

    @Provides
    @Named("mongoDatabase")
    fun provideMongoDatabase(@Named("databaseClient") mongoClient: MongoClient): MongoDatabase {
        return mongoClient.getDatabase("EmployeeDetails")
    }

}