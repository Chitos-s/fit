package com.example.fit

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fit.FitMain
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.example.fit.R

class UserInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val changePasswordButton: MaterialButton = view.findViewById(R.id.changePasswordButton)
        val logoutButton: MaterialButton = view.findViewById(R.id.logoutButton)

        changePasswordButton.setOnClickListener {
            requireParentFragment().findNavController().navigate(R.id.action_userInfoFragment_to_passwordUpdateFragment)
        }

        logoutButton.setOnClickListener {
            val intent = Intent(requireContext(), com.example.fit.ui.auth.MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
        return view
    }
}
