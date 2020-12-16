package di

import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import java.util.*
import javax.inject.Named

@Module
class MapperProvide {

    @Provides
    @Named("mapper")
    fun provideObjectMapper():ObjectMapper
    {
        return ObjectMapper()
    }
}