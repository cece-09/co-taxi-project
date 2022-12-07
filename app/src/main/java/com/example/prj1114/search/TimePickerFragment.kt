package com.example.prj1114.search

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

import java.util.Calendar

class TimePickerFragment:DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cal = Calendar.getInstance()

        return TimePickerDialog(
            requireContext(),
            null,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        )
    }
}