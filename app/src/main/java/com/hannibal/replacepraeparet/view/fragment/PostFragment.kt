package com.hannibal.replacepraeparet.view.fragment

import android.location.Geocoder
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.putMarkerButton.setOnClickListener {
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
            openBottomSheet(center)
        }



        return binding.root
    }

    private fun openBottomSheet(center: LatLng) {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val address = geocoder.getFromLocation(center.latitude, center.longitude, 1)
        val bundle = Bundle()
        val dialog = PostBottomSheetFragment()
        if (address!!.size > 0) {
            val addressText = address[0].getAddressLine(0)
            bundle.putString("address_text", addressText)
            dialog.arguments = bundle
            dialog.show(childFragmentManager, dialog.tag)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
}