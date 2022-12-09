package com.example.prj1114

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.util.Log

import androidx.fragment.app.activityViewModels
import com.example.prj1114.AutoCompleteFragment.TimeDeparturesArrivals.placeName

import com.example.prj1114.databinding.FragmentAutoCompleteBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceLikelihood
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import java.util.*

// https://developers.google.com/maps/documentation/places/android-sdk/autocomplete?hl=ko#option_1_embed_an_autocompletesupportfragment

class AutoCompleteFragment: Fragment() {

    private var _binding: FragmentAutoCompleteBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore



    private lateinit var placesClient: PlacesClient

    private val viewModel: SearchViewModel by activityViewModels()

    object TimeDeparturesArrivals {
        lateinit var placeId: String
        lateinit var placeName: String
        lateinit var placeLatLng: String
        lateinit var placeaddress: String
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Places.initialize(requireContext(), getString(R.string.google_maps_key), Locale.KOREA)
        placesClient = Places.createClient(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAutoCompleteBinding.inflate(inflater, container, false)


        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.ID, Place.Field.NAME,
                Place.Field.LAT_LNG, Place.Field.ADDRESS
            )
        )

            /// Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                placeName = place.name!!

                val data = hashMapOf(
                    "n" to placeName
                )

                db.collection("cities")
                    .add(data)
                    .addOnSuccessListener { documentReference ->
                        Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("TAG", "Error adding document", e)
                    }

                Log.i("TAG", "Place: ${place.name}, ${place.id}")

            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: $status")
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}
