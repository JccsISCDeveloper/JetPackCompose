package com.jccsisc.irepcp.core

import com.jccsisc.irepcp.core.enums.StatusEnum

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core
 * Created by Julio Cesar Camacho Silva on 15/11/22
 */
class MyResult<out T>(val status: StatusEnum, val data: T?, val message: String?) {
    companion object {
        fun <T> loading(): MyResult<T> = MyResult(StatusEnum.LOADING, null, null)

        fun <T> success(data: T?): MyResult<T> = MyResult(StatusEnum.SUCCESS, data, null)

        fun <T> error(data: T?, msg: String?,): MyResult<T> = MyResult(StatusEnum.ERROR, data, msg)
    }
}

