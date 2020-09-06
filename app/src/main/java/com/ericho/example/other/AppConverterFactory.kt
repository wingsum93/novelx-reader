package com.ericho.example.other

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class AppConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type === Page::class.java) HtmlPageAdapter() else
            return super.responseBodyConverter(type, annotations, retrofit)
    }
}