package com.hannibal.replacepraeparet.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.databinding.FragmentPostBinding
import java.util.*

class PostFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentPostBinding
    private lateinit var mMap: GoogleMap
    private lateinit var mp: MarkerOptions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.putMarkerButton.setOnClickListener {
            openBottomSheet()
            val center = mMap.cameraPosition.target
            val location = LatLng(center.latitude, center.longitude)
            val str =
                java.lang.String.format(
                    Locale.US,
                    "%f, %f",
                    center.latitude,
                    center.longitude
                )
            mMap.addMarker(MarkerOptions().position(location).title(str).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 7f))
            Log.d("location_center", "$center")
            Log.d("location_str", "$str")
        }



        return binding.root
    }

    private fun openBottomSheet() {
        val dialog = PostBottomSheetFragment()
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
}