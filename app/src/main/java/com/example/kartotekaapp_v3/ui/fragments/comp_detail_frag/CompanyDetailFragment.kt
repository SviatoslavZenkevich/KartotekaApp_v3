package com.example.kartotekaapp_v3.ui.fragments.comp_detail_frag

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.kartotekaapp_v3.R
import com.example.kartotekaapp_v3.data.Company

import com.example.kartotekaapp_v3.databinding.FragmentCompanyDetailBinding
import com.example.kartotekaapp_v3.network.CompanyApi
import com.example.kartotekaapp_v3.ui.viewmodels.CompanyViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CompanyDetailFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentCompanyDetailBinding
    private lateinit var viewModel: CompanyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompanyDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)

    // Add menu
    setHasOptionsMenu(true)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        val unp = arguments?.getString("CompanyUnp")!!.toInt()

        val apiInterface = CompanyApi.create().getCompanyByTaxId(unp)


        apiInterface.enqueue(object : Callback<Company> {
            override fun onResponse(call: Call<Company>?, response: Response<Company>?) {
                Log.d("mylog", "onResponse success ${response?.body()?.data?.tax_id}")
                binding.tvUNP.text = response?.body()?.data?.tax_id.toString()
                binding.tvCompanyName.text = response?.body()?.data?.short_name_egr.toString()
                binding.tvRegDate.text = response?.body()?.data?.registered_egr.toString()
                binding.tvLegalAddress.text = response?.body()?.data?.legal_address.toString()
                binding.tvEGRStatus.text = response?.body()?.data?.status_egr.toString()
            }

            override fun onFailure(call: Call<Company>?, t: Throwable?) {
                Log.d("mylog", "onFailure : ${t?.message}")
            }
        })
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteFavComp()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteFavComp() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            val companyName = binding.tvCompanyName.text.toString()
            val companyId = binding.tvUNP.text.toString()

            viewModel.deleteFavoriteCompany(companyId)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${companyName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_companyDetailFragment_to_companyListFragment)
        }

        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${binding.tvCompanyName.text.toString()}?")
        builder.setMessage("Are you sure you want to delete ${binding.tvCompanyName.text.toString()}?")
        builder.create().show()
    }
}