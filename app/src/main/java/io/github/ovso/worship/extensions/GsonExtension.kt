package io.github.ovso.worship.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(jsonArray: String): T {
    return Gson().fromJson(jsonArray, object : TypeToken<T>() {}.type)
}
