package com.example.proton.ui.management

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.R
import com.example.proton.adapter.ListProductAdapter
import com.example.proton.adapter.ListStoreAdapter
import com.example.proton.databinding.FragmentManagementBinding
import com.example.proton.model.FakeProductDataSource
import com.example.proton.model.FakeStoreDataSource


class ManagementFragment : Fragment() {

    private var tabName: String? = null

    private var _binding: FragmentManagementBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManagementBinding.inflate(layoutInflater, container, false)
        showRecyclerList()
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabName = arguments?.getString(ARG_TAB)
        val dataProduct = FakeProductDataSource.dummyProduct
        val dataStore = FakeStoreDataSource.dummyStore



        if(tabName == TAB_PRODUCT){
            if(dataProduct.isEmpty()){
                val emptyText = getString(R.string.empty_management, "produk")
                val messageText = getString(R.string.message_empty, "produk")
                val buttonText = getString(R.string.button_empty, "produk")
                val imageRes = R.drawable.empty_product

                bindingSetting(emptyText, messageText, buttonText, imageRes)

            }else{
                disabelEmptyLayout()
                binding?.viewCard?.setData(ListProductAdapter(dataProduct))

            }
        }else{
            if(dataStore.isEmpty()){

                val emptyText = getString(R.string.empty_management, "toko")
                val messageText = getString(R.string.message_empty, "toko")
                val buttonText = getString(R.string.button_empty, "toko")
                val imageRes = R.drawable.empty_store

                bindingSetting(emptyText, messageText, buttonText, imageRes)

            }else{
                disabelEmptyLayout()
                binding?.viewCard?.setData(ListStoreAdapter(dataStore))
            }

        }
    }

    private fun disabelEmptyLayout(){
        binding?.apply {
            emptyLayout.visibility = View.GONE
        }
    }

    private fun bindingSetting(emptyText: String, messageText: String, buttonText: String, imageRes: Int){
        binding?.apply {
            emptyTextView.text = emptyText
            messageTextView.text = messageText
            addButton.text = buttonText
            emptyImageView.setImageResource(imageRes)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun RecyclerView.setData(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun showRecyclerList() {
        val layoutManager = LinearLayoutManager(requireActivity())
        binding?.viewCard?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding?.viewCard?.addItemDecoration(itemDecoration)
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