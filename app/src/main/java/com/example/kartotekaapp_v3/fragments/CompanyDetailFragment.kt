package com.example.kartotekaapp_v3.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.kartotekaapp_v3.R
import com.example.kartotekaapp_v3.data.Company

import com.example.kartotekaapp_v3.databinding.FragmentCompanyDetailBinding
import com.example.kartotekaapp_v3.network.CompanyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CompanyDetailFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentCompanyDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompanyDetailBinding.inflate(inflater, container, false)
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
}