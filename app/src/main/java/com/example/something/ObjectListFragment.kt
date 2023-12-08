package com.example.something

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.something.databinding.FragmentObjectListBinding

class ObjectListFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private val sharedViewModel: SharedViewModel by viewModels()
    private val adapter = ObjectAdapter()

    private var _binding: FragmentObjectListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentObjectListBinding.inflate(inflater, container, false)
        val view = binding.root

        databaseHelper = DatabaseHelper(requireContext())
        recyclerView = binding.objectListRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        sharedViewModel.objectAddedEvent.observe(viewLifecycleOwner) {
            if (it == true) {
                adapter.submitList(databaseHelper.getAllObjects())
                sharedViewModel.objectAddedEvent.value = false
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
