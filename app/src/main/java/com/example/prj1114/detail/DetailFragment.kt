package com.example.prj1114.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.prj1114.Act06Detail
import com.example.prj1114.databinding.DetailFragmentBinding
import com.example.prj1114.viewmodel.DetailViewModel
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.IOException

class DetailFragment :Fragment() {
    private var mbinding: DetailFragmentBinding? = null
    private val binding get() = mbinding!!
    private lateinit var viewmodel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = DetailFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        val view = binding.root

        lifecycleScope.launch {
            viewmodel.getTargetTeam()
        }

        viewmodel.isAwait.observe(viewLifecycleOwner) {
            when(it) {
                true -> loading()
                false -> show()
            }
        }

        /** navigate to chat */
        binding.button.setOnClickListener{
            Toast.makeText(context, "navigate to Chat fragment", Toast.LENGTH_SHORT).show()
            // callFragment()
        }

        return view
    }

    private fun loading() {
        binding.loading.visibility = View.VISIBLE
    }
    private fun show() {
        binding.loading.visibility = View.GONE
        binding.timeText.text = viewmodel.time
        binding.maxText.text = viewmodel.max
        binding.startText.text = viewmodel.start
        binding.endText.text = viewmodel.end
    }
    private fun callFragment(){
        (activity as Act06Detail).openChatFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }
}