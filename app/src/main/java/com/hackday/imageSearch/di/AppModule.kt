package com.hackday.imageSearch.di

import com.hackday.imageSearch.repository.PhotoInfoRepository
import com.hackday.imageSearch.repository.PhotoInfoRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    factory<PhotoInfoRepository> { (PhotoInfoRepositoryImpl()) }
}