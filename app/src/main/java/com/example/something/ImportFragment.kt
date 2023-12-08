package com.example.something

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.something.databinding.FragmentImportBinding

class ImportFragment : Fragment() {
    private lateinit var databaseHelper: DatabaseHelper
    private val sharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentImportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImportBinding.inflate(inflater, container, false)
        val view = binding.root

        databaseHelper = DatabaseHelper(requireContext())

        val objectNameInput = binding.objectNameInput
        val addButton = binding.addButton

        addButton.setOnClickListener {
            val objectName = objectNameInput.text.toString()
            databaseHelper.addObject(objectName)
            objectNameInput.text.clear()
            Toast.makeText(requireContext(), "Object added!", Toast.LENGTH_SHORT).show()

            sharedViewModel.objectAddedEvent.value = true
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
