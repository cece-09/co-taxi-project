package com.example.prj1114

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MapViewModel: ViewModel() {







    private var originName = MutableLiveData<String>()
    private var destinationName = MutableLiveData<String>()
    private var originLatLng = MutableLiveData<Double>()
    private var destinationLatLng = MutableLiveData<Double>()







}