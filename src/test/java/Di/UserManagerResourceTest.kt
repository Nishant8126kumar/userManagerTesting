package Di

import dagger.Module
import dagger.Provides

@Module
class UserManagerResourceTest {
    @Provides
    fun provideUserManagerResourceTest():UserManagerResourceTest
    {
        return provideUserManagerResourceTest()
    }
}