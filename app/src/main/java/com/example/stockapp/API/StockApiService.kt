package com.example.stockapp.API
import com.example.stockapp.Data.StockData

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {
    @GET("query")
    suspend fun getStockDailyData(
        @Query("function") function: String = "TIME_SERIES_DAILY",
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String
    ): Response<StockData>

}
