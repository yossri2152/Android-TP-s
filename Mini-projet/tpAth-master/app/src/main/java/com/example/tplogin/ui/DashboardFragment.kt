package com.example.tplogin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tplogin.databinding.FragmentDashbordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class DashboardFragment : Fragment() {
    lateinit var user: FirebaseUser
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding =
            FragmentDashbordBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        setData(binding)
        return binding.root}

    private fun setData(binding: FragmentDashbordBinding) {
        binding.tvDetails.text = user.email.toString()

    }


}