package com.example.stockapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.stockapp.databinding.FragmentSigninBinding

class Signin : Fragment() {
    private lateinit var signinBinding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signinBinding = FragmentSigninBinding.inflate(inflater, container, false)
        return signinBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signinBinding.SignInButton.setOnClickListener {
            findNavController().navigate(R.id.action_signin_to_home2)
        }
    }
}