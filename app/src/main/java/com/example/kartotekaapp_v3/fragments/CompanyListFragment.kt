package com.example.kartotekaapp_v3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kartotekaapp_v3.R
import com.example.kartotekaapp_v3.adapters.CompanyAdapter
import com.example.kartotekaapp_v3.databinding.CompanyItemBinding
import com.example.kartotekaapp_v3.databinding.FragmentCompanyBinding
import com.example.kartotekaapp_v3.databinding.FragmentCompanyListBinding
import com.example.kartotekaapp_v3.room.FavoriteCompanies
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyListFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentCompanyListBinding
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { CompanyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCompanyListBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun init() {
        val viewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)
        recyclerView = binding.rcvCompanyList
        recyclerView.adapter = adapter
        viewModel.allCompanies
    }
//    private var onItemClickListener: ((FavoriteCompanies) -> Unit)? = null
//
//    fun setonItemClickListener(listener: (FavoriteCompanies) -> Unit) {
//        onItemClickListener = listener
//        val unp = binding.tv.text.toString().trim()
//        if (unp.isNotEmpty()) {
//            var bundle = bundleOf("CompanyUnp" to unp)
//            navController.navigate(R.id.companyFragment, bundle)
//
//        } else {
//            Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }
        private fun init(view: View) {
            navController = Navigation.findNavController(view)
        }
    }
