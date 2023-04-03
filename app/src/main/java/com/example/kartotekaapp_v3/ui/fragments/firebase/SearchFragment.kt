package com.example.kartotekaapp_v3.ui.fragments.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.kartotekaapp_v3.R
import com.example.kartotekaapp_v3.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)





        binding.btSearch.setOnClickListener {
            val unp = binding.etSearchUnp.text.toString().trim()
            if (unp.isNotEmpty()) {
                var bundle = bundleOf("CompanyUnp" to unp)
                navController.navigate(R.id.companyFragment, bundle)

            } else {
                Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btSelectList.setOnClickListener {
            navController.navigate(R.id.action_searchFragment_to_companyListFragment)
        }
    }
    private fun init(view: View) {
        navController = Navigation.findNavController(view)
    }
}
