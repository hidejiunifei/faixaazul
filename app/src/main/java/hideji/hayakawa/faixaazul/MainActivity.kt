// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package hideji.hayakawa.faixaazul

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import hideji.hayakawa.faixaazul.coordinate.UtmCoordinate
import kotlinx.coroutines.async
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // [START maps_android_add_map_codelab_ktx_coroutines]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        lifecycleScope.launchWhenCreated {
            // Get map
            val googleMap = mapFragment.awaitMap()

            // Wait for map to finish loading
            googleMap.awaitMapLoad()

            lateinit var polylineOptionsArray : ArrayList<PolylineOptions>

            val result1 = async {
                polylineOptionsArray = callApi()
            }

            result1.await()

            polylineOptionsArray.forEach {
                googleMap.addPolyline(it)
            }

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                googleMap.isMyLocationEnabled = true
                fusedLocationClient.lastLocation.addOnSuccessListener(this@MainActivity) {
                        location ->
                    if (location != null) {
                        lastLocation = location
                        val currentLatLong  = LatLng(location.latitude, location.longitude)
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))
                    }
                }
            }
        }
    }

    companion object{
        private val faixasAzuis = URL("https://bhmap.pbh.gov.br/v2/BHMapProxy.jsp?server=https://geoservicos.pbh.gov.br/geoserver/wfs?service=WFS&version=1.0.0&request=GetFeature&typeName=ide_bhgeo:ESTACIONAMENTO_ROTATIVO_SEGUNDA_SEXTA&outputFormat=application%2Fjson")
        private lateinit var t : JSONObject

        fun callApi() : ArrayList<PolylineOptions>{
            val arraylist = ArrayList<PolylineOptions>()

            val t = Thread() {
                with(faixasAzuis.openConnection() as HttpURLConnection) {
                    val inputStream = DataInputStream(inputStream)
                    val reader = BufferedReader(InputStreamReader(inputStream))

                    t = JSONObject(reader.readText())

                    val features = t.getJSONArray("features")

                    for (i in 0 until features.length())
                    {
                        val item = features.getJSONObject(i)
                        val coordinates =
                            item.getJSONObject("geometry").getJSONArray("coordinates")
                                .getJSONArray(0)

                        for (j in 0 until coordinates.length()-1)
                        {
                            val firstUTM = UtmCoordinate(
                                23,
                                coordinates.getJSONArray(j).get(0).toString().toDouble(),
                                coordinates.getJSONArray(j).get(1).toString().toDouble())
                            val secondUTM = UtmCoordinate(
                                23,
                                coordinates.getJSONArray(j+1).get(0).toString().toDouble(),
                                coordinates.getJSONArray(j+1).get(1).toString().toDouble())
                            val firstLatLng = firstUTM.utm2Deg()
                            val secondLatLng = secondUTM.utm2Deg()

                            val polylineOptions = PolylineOptions()
                                .add(LatLng(firstLatLng.latitude, firstLatLng.longitude))
                                .add(LatLng(secondLatLng.latitude, secondLatLng.longitude))
                                .color(
                                    ConvertTimeToColor.convertTimeToColor(
                                        item.getJSONObject("properties")
                                            .getString("TEMPO_PERMANENCIA")
                                    )
                                )

                                arraylist.add(polylineOptions)
                        }
                    }
                }
            }

            t.start()
            t.join()

            return arraylist
        }
    }
}
