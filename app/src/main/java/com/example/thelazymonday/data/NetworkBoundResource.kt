package com.example.thelazymonday.data

import com.example.thelazymonday.utils.AppExecutor

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutor: AppExecutor) {
}