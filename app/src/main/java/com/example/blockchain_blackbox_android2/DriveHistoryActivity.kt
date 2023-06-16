package com.example.blockchain_blackbox_android2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.blockchain_blackbox_android2.databinding.ActivityDriveHistoryBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection


class DriveHistoryActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityDriveHistoryBinding
    private lateinit var mapFragment: MapFragment
    private lateinit var naverMap: NaverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriveHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        //mapFragment.getMapAsync(this)

        binding.ivMapSearch.setOnClickListener{
            var mapSearchInput = binding.etMapSearch.text.toString()
           // var mapSearchInput="서울특별시 동작구"
            moveMapToLocation(mapSearchInput)
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        // mapFragment의 위치 이동
       // moveMapToLocation()
    }

    fun moveMapToLocation(mapSearchInput: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val clientId = "wxw9zimbo4"
            val clientSecret = "xLNrTgxyNYep62ayOzgZ1w3rUd1aLC2LxgK0flsJ"
            val addr: String = URLEncoder.encode(mapSearchInput, "UTF-8")
            val apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=$addr"

            withContext(Dispatchers.IO) {
                val url = URL(apiURL)
                val con = url.openConnection() as HttpURLConnection
                con.requestMethod = "GET"
                con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId)
                con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret)

                try {
                    val responseCode = con.responseCode
                    val responseMessage = con.responseMessage

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        val response = con.inputStream.bufferedReader().readText()
                        val responseJson = JSONObject(response)

                        val addresses = responseJson.getJSONArray("addresses")
                        if (addresses.length() == 0) {
                            Log.d("no result", "")
                        } else {
                            val firstAddress = addresses.getJSONObject(0)
                            val latitude = firstAddress.getString("y").toDouble()
                            val longitude = firstAddress.getString("x").toDouble()
                            Log.d("latitude", latitude.toString())
                            Log.d("longitude", longitude.toString())
                            runOnUiThread {
                                mapFragment?.getMapAsync { naverMap: NaverMap ->
                                    val target = LatLng(latitude, longitude)
                                    naverMap.moveCamera(
                                        com.naver.maps.map.CameraUpdate.scrollTo(
                                            target
                                        )
                                    )
                                }
                            }
                        }
                    } else {
                        Log.d("Response error: ", responseCode.toString())
                        Log.d("Response message", responseMessage.toString())
                    }
                } catch (e: Exception) {
                    Log.d("HTTP Error", "")
                    e.printStackTrace()
                } finally {
                    con.disconnect()
                }
            }
        }
    }

}