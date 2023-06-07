package com.example.proton.ui.management

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter internal constructor(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        val fragment = ManagementFragment()
        val bundle = Bundle()
        if (position == 0) {
            bundle.putString(ManagementFragment.ARG_TAB, ManagementFragment.TAB_PRODUCT)
        } else {
            bundle.putString(ManagementFragment.ARG_TAB, ManagementFragment.TAB_STORE)
        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}