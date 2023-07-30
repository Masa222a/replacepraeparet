package com.hannibal.replacepraeparet.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.databinding.FragmentPostBinding
import java.util.*

class PostFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentPostBinding
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var location = LatLng(0.0,0.0)

        mMap.setOnMapClickListener { tapLocation: LatLng ->
            location = LatLng(tapLocation.latitude, tapLocation.longitude)
            val str =
                java.lang.String.format(
                    Locale.US,
                    "%f, %f",
                    tapLocation.latitude,
                    tapLocation.longitude
                )
            mMap.addMarker(MarkerOptions().position(location).title(str))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14f))
        }
    }
}