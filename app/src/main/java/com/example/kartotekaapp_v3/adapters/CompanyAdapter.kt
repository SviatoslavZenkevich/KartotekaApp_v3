package com.example.kartotekaapp_v3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.navArgument
import androidx.recyclerview.widget.RecyclerView
import com.example.kartotekaapp_v3.R
import com.example.kartotekaapp_v3.databinding.CompanyItemBinding
import com.example.kartotekaapp_v3.fragments.CompanyListFragment
import com.example.kartotekaapp_v3.fragments.CompanyListViewModel
import com.example.kartotekaapp_v3.room.FavoriteCompanies


class CompanyAdapter(val listener: Listener?) :
    RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    private var companyList = emptyList<FavoriteCompanies>()


    class CompanyViewHolder(
        private val binding: CompanyItemBinding,
        val listener: Listener?
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(favoriteCompanies: FavoriteCompanies, listener: Listener?) {

            binding.idTxt.text = favoriteCompanies.id.toString()
            binding.tvTitle.text = favoriteCompanies.companyName
            binding.tvTaxID.text = favoriteCompanies.companyId

            binding.itemLayout.setOnClickListener {
                listener?.onClick(favoriteCompanies)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {

        val binding =
            CompanyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompanyViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val currentItem = companyList[position]
        holder.bind(currentItem, listener)
    }


    fun setData(favoriteCompanies: List<FavoriteCompanies>) {
        this.companyList = favoriteCompanies
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(favoriteCompanies: FavoriteCompanies)
    }
}

