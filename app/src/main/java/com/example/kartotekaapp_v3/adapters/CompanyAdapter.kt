package com.example.kartotekaapp_v3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kartotekaapp_v3.R
import com.example.kartotekaapp_v3.databinding.CompanyItemBinding
import com.example.kartotekaapp_v3.room.FavoriteCompanies

class CompanyAdapter : ListAdapter<FavoriteCompanies, CompanyAdapter.Holder>(Comparator()) {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CompanyItemBinding.bind(view)

        fun bind(favoriteCompany: FavoriteCompanies) = with(binding) {
            tvTitle.text = favoriteCompany.companyName
            tvStatus.text = favoriteCompany.companyId.toString()
        }
    }

    class Comparator : DiffUtil.ItemCallback<FavoriteCompanies>() {
        override fun areItemsTheSame(oldItem: FavoriteCompanies, newItem: FavoriteCompanies): Boolean {
            return oldItem.companyName == newItem.companyName
        }

        override fun areContentsTheSame(oldItem: FavoriteCompanies, newItem: FavoriteCompanies): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.company_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }


   }