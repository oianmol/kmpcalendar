package com.baseio.kmm.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

expect fun platformModule(): Module

fun initSharedDependencies() = startKoin {
  modules(platformModule())
}

