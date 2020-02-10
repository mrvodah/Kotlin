package com.example.kotlin.screen.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlin.R
import com.example.kotlin.model.Lego

/**
 * A simple [Fragment] subclass.
 */
class DetailLegoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_lego, container, false)
    }

    companion object {
        fun getInstance(lego: Lego): DetailLegoFragment {
            val detailLegoFragment = DetailLegoFragment()
            val bundle = Bundle()
            bundle.putParcelable("Lego", lego)
            detailLegoFragment.arguments = bundle
            return detailLegoFragment
        }
    }

}
