package com.app.moviesapp.di

import com.app.moviesapp.data.DataSourceImpl
import com.app.moviesapp.domain.DataSource
import com.app.moviesapp.domain.Repo
import com.app.moviesapp.domain.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract  fun bindRepoImpl(repoImpl: RepoImpl) : Repo

    @Binds
    abstract  fun bindDataSourceImpl(dataSourceImpl: DataSourceImpl) : DataSource
}