package di

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ConfigModule {

    private val hostName = "http://localhost"
    private val portNumber = 9085

    @Provides
    @Named("host")
    fun provideHost(): String {
        return hostName
    }

    @Provides
    @Named("port")
    fun providePort(): Int {
        return portNumber
    }
}