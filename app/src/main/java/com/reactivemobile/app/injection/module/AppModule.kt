package com.reactivemobile.app.injection.module

import com.reactivemobile.app.data.remote.Repository
import com.reactivemobile.app.ui.main.MainContract
import com.reactivemobile.app.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun providesRepository(): Repository {
        return Repository()
    }

    @Provides
    fun providesMainPresenter(repository: Repository): MainContract.Presenter {
        return MainPresenter(repository)
    }
}