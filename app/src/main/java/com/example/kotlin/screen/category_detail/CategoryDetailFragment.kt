package com.example.kotlin.screen.category_detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlin.R

/**
 * A simple [Fragment] subclass.
 */
class CategoryDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_detail, container, false)
    }

    companion object {
        fun getInstance(type: String): CategoryDetailFragment {
            val categoryDetailFragment = CategoryDetailFragment()
            val bundle = Bundle()
            bundle.putString("Type", type)
            categoryDetailFragment.arguments = bundle
            return categoryDetailFragment
        }
    }

}
