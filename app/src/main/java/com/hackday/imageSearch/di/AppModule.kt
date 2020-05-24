package com.hackday.imageSearch.di

import com.hackday.imageSearch.repository.PhotoInfoRepository
import com.hackday.imageSearch.repository.PhotoInfoRepositoryImpl
import com.hackday.imageSearch.repository.PhotoTagRepository
import org.koin.dsl.module

val appModule = module {
    factory<PhotoInfoRepository> { (PhotoInfoRepositoryImpl()) }
    factory<PhotoTagRepository> { (PhotoInfoRepositoryImpl()) }
}