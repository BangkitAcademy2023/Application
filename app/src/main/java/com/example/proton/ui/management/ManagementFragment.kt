package com.example.proton.ui.management

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.MainActivity
import com.example.proton.R
import com.example.proton.adapter.ListProductAdapter
import com.example.proton.adapter.ListStoreAdapter
import com.example.proton.data.remote.Result
import com.example.proton.data.remote.response.DataItem
import com.example.proton.databinding.FragmentManagementBinding
import com.example.proton.model.ProductModel
import com.example.proton.model.StoreModel
import com.example.proton.model.UserModel
import com.example.proton.ui.ViewModelFactory
import com.example.proton.ui.product.ProductActivity
import com.example.proton.ui.recommendation.RecommendationActivity
import com.example.proton.ui.store.StoreActivity
import kotlinx.coroutines.launch

class ManagementFragment : Fragment() {

    private val managementViewModel: ManagementViewModel by viewModels{
        ViewModelFactory.getInstance()
    }

    private var tabName: String? = null

    private var _binding: FragmentManagementBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagementBinding.inflate(layoutInflater, container, false)
        showRecyclerList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabName = arguments?.getString(ARG_TAB)

        if (tabName == TAB_PRODUCT) {
            val dataProduct: List<ProductModel> = managementViewModel.groupedProduct.value.values.flatten()
            if (dataProduct.isEmpty()) {
                bindingSetting(
                    getString(R.string.empty_management, "produk"),
                    getString(R.string.message_empty, "produk"),
                    getString(R.string.button_empty, "produk"),
                    R.drawable.empty_product
                )
            } else {
                disabelEmptyLayout()
                searching(true)
                managementViewModel.getAllProduct()
                managementViewModel.listProduct.observe(this){ result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val data = result.data
                            if(data.data != null){
                                val listProduct = data.data
                                binding.viewCard.setData(ListProductAdapter(listProduct as List<DataItem>))
                            }

                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), "Data Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                binding.fabButton.setOnClickListener {
                    val intent = Intent(requireContext(), ProductActivity::class.java)
                    startActivity(intent)
                }
            }
        } else {
            val dataStore: List<StoreModel> = managementViewModel.groupedStore.value.values.flatten()
            if (dataStore.isEmpty()) {
                bindingSetting(
                    getString(R.string.empty_management, "toko"),
                    getString(R.string.message_empty, "toko"),
                    getString(R.string.button_empty, "toko"),
                    R.drawable.empty_store
                )
            } else {
                disabelEmptyLayout()
                searching(false)
                viewLifecycleOwner.lifecycleScope.launch {
                    managementViewModel.groupedStore.collect { groupedStore ->
                        binding.viewCard.setData(ListStoreAdapter(groupedStore.values.flatten()))
                    }
                }

                binding.fabButton.setOnClickListener {
                    val intent = Intent(requireContext(), RecommendationActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun searching(status: Boolean) {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Kosongkan atau biarkan sesuai kebutuhan
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Panggil fungsi search() di ManagementViewModel
                Log.i("search", s.toString())
                if (status) managementViewModel.searchProduct(s.toString())
                else managementViewModel.searchStore(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // Kosongkan atau biarkan sesuai kebutuhan
            }
        })
    }

    private fun disabelEmptyLayout() {
        binding.emptyLayout.visibility = View.GONE
    }

    private fun bindingSetting(emptyText: String, messageText: String, buttonText: String, imageRes: Int) {
        binding.apply {
            emptyTextView.text = emptyText
            messageTextView.text = messageText
            addButton.text = buttonText
            emptyImageView.setImageResource(imageRes)
        }
    }

    private fun RecyclerView.setData(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
    }

    private fun showRecyclerList() {
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.viewCard.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.viewCard.addItemDecoration(itemDecoration)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    companion object {
        const val ARG_TAB = "tab_name"
        const val TAB_PRODUCT = "Produk"
        const val TAB_STORE = "Toko"
    }
}