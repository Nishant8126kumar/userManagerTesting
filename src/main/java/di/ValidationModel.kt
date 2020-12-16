package di

import dagger.Module
import dagger.Provides
import validation.Validation
import javax.inject.Named

@Module
class ValidationModel {

    @Provides
    @Named("validation")
    fun provideValidation():Validation
    {
        return Validation()
    }
}