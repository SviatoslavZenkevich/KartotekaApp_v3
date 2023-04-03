package com.example.kartotekaapp_v3.ui.fragments.comp_list_frag

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kartotekaapp_v3.R
import com.example.kartotekaapp_v3.adapters.CompanyAdapter
import com.example.kartotekaapp_v3.databinding.FragmentCompanyListBinding
import com.example.kartotekaapp_v3.room.FavoriteCompanies
import com.example.kartotekaapp_v3.ui.viewmodels.CompanyListViewModel

class CompanyListFragment : Fragment(), CompanyAdapter.Listener {
    private lateinit var binding: FragmentCompanyListBinding
    private lateinit var mCompanyListViewModel: CompanyListViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCompanyListBinding.inflate(layoutInflater, container, false)

        // Add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun init() {
        // Recyclerview
        val adapter = CompanyAdapter(this)
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CompanyListViewModel
        mCompanyListViewModel = ViewModelProvider(this).get(CompanyListViewModel::class.java)
        mCompanyListViewModel.readAllData.observe(viewLifecycleOwner, Observer { favoriteCompanies ->
            adapter.setData(favoriteCompanies)
        })
    }
//    private var onItemClickListener: ((FavoriteCompanies) -> Unit)? = null
//
//    fun setonItemClickListener(listener: (FavoriteCompanies) -> Unit) {
//        onItemClickListener = listener
//        val unp = mbinding.tvTaxID.text.toString().trim()
//        if (unp.isNotEmpty()) {
//            var bundle = bundleOf("CompanyUnp" to unp)
//            navController.navigate(R.id.companyDetailFragment, bundle)
//
//        } else {
//            Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        init()
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
    }

    override fun onClick(favoriteCompanies: FavoriteCompanies) {
        Toast.makeText(
            requireContext(),
            "Нажали на : ${favoriteCompanies.companyName}",
            Toast.LENGTH_SHORT
        ).show()
        val unp = favoriteCompanies.companyId
        var bundle = bundleOf("CompanyUnp" to unp)
        navController.navigate(R.id.companyDetailFragment, bundle)

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
           deleteAllFav()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllFav() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
           mCompanyListViewModel.deleteAllCompanies()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }
}



////    private var onItemClickListener: ((FavoriteCompanies) -> Unit)? = null
//
////    fun setonItemClickListener(listener: (FavoriteCompanies) -> Unit) {
////        onItemClickListener = listener
////        val unp = binding.tv.text.toString().trim()
////        if (unp.isNotEmpty()) {
////            var bundle = bundleOf("CompanyUnp" to unp)
////            navController.navigate(R.id.companyFragment, bundle)
////
////        } else {
////            Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
////        }
////    }
//}
//@AndroidEntryPoint
//class CompanyListFragment : Fragment() {
//
//    private lateinit var navController: NavController
//    private lateinit var binding: FragmentCompanyListBinding
//    lateinit var recyclerView: RecyclerView
//    private val adapter by lazy { CompanyAdapter() }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        binding = FragmentCompanyListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//    private fun init() {
//        val viewModel = ViewModelProvider(this)[CompanyListViewModel::class.java]
//        recyclerView = binding.rcvCompanyList
//        recyclerView.adapter = adapter
//        viewModel.companyList
//    }
////    private var onItemClickListener: ((FavoriteCompanies) -> Unit)? = null
//
////    fun setonItemClickListener(listener: (FavoriteCompanies) -> Unit) {
////        onItemClickListener = listener
////        val unp = binding.tv.text.toString().trim()
////        if (unp.isNotEmpty()) {
////            var bundle = bundleOf("CompanyUnp" to unp)
////            navController.navigate(R.id.companyFragment, bundle)
////
////        } else {
////            Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
////        }
////    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        init(view)
//        init()
//    }
//        private fun init(view: View) {
//            navController = Navigation.findNavController(view)
//        }
//    }
