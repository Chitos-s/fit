package com.example.fit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class PasswordUpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_password, container, false)

        val backButton: ImageButton = view.findViewById(R.id.backButton)


        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
} 