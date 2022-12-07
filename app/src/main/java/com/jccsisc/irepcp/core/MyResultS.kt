package com.jccsisc.irepcp.core

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
sealed class MyResultS<T>(val data: T? = null, val msg: String? = null) {
    class Loading<T>(data: T? = null): MyResultS<T>(data)
    class Success<T>(data: T?): MyResultS<T>(data)
    class Failure<T>(msg: String, data: T? = null): MyResultS<T>(data, msg)
}
