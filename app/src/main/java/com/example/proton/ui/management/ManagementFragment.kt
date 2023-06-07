package com.example.proton.ui.management

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proton.R
import com.example.proton.databinding.FragmentManagementBinding


class ManagementFragment : Fragment() {

    private var tabName: String? = null

    private var _binding: FragmentManagementBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManagementBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabName = arguments?.getString(ARG_TAB)

        val emptyText = if (tabName == TAB_PRODUCT) {
            getString(R.string.empty_management, "produk")
        } else {
            getString(R.string.empty_management, "toko")
        }

        val messageText = getString(R.string.message_empty, tabName)
        val buttonText = getString(R.string.button_empty, tabName)

        val imageRes = if (tabName == TAB_PRODUCT) {
            R.drawable.empty_product
        } else {
            R.drawable.empty_store
        }

        binding?.apply {
            emptyTextView.text = emptyText
            messageTextView.text = messageText
            addButton.text = buttonText
            emptyImageView.setImageResource(imageRes)
        }
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