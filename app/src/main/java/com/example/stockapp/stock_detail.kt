package com.example.stockapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.stockapp.API.StockApiService
import com.example.stockapp.Data.DailyData
import com.example.stockapp.Data.MetaData
import com.example.stockapp.Data.StockData
import com.example.stockapp.databinding.FragmentSigninBinding
import com.example.stockapp.databinding.FragmentStockDetailBinding
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class stock_detail : Fragment() {
    private lateinit var stock_detailBinding : FragmentStockDetailBinding

    val baseURL = "https://www.alphavantage.co/"
    val symbol = stock_detailBinding.textSymbol.text.toString()
    val apiKey = "K8V2KK0RIPEX8SQU"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        stock_detailBinding = FragmentStockDetailBinding.inflate(inflater, container, false)
        return stock_detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDetails()
    }

    private fun showDetails(){
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI: StockApiService = retrofit.create(StockApiService::class.java)

        lifecycleScope.launch {
            try {
                val response: Response<StockData> = retrofitAPI.getStockDailyData(
                    function = "TIME_SERIES_DAILY",
                    symbol = symbol,
                    apiKey = apiKey
                )
                if(response.isSuccessful){
                    val stockData: StockData? = response.body()
                    if(stockData != null){
                        val metaData: MetaData = stockData.metaData
                        val timeSeriesDaily: Map<String, DailyData> = stockData.timeSeriesDaily
                    }
                }else{
                    println("Error: ${response.code()}")
                }
            }catch (e: Exception){
                println("Error: ${e.message}")
            }

        }


    }
}

