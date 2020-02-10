package com.example.kotlin.screen.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.kotlin.R
import com.example.kotlin.model.Lego
import com.example.kotlin.screen.base.recyclerview.OnItemClickListener
import com.example.kotlin.screen.detail.DetailLegoActivity
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment(), OnItemClickListener<Lego>,
    SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewDataBinding: ViewDataBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onItemClick(item: Lego, position: Int) {
        startActivity(DetailLegoActivity.getIntent(context!!, item))
    }

    override fun onRefresh() {
        refreshDataForAdapter()
    }

    private fun refreshDataForAdapter() {
        viewModel.getIdItemFavorite()
        swipeRefreshFavorite.isRefreshing = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        viewModel.getIdItemFavorite()
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerFavorite()
        initSwipeRefreshListener()
    }

    private fun initSwipeRefreshListener() {
        swipeRefreshFavorite.setOnRefreshListener(this)
        swipeRefreshFavorite.setColorSchemeResources(
            R.color.color_text_blue,
            R.color.color_text_blue,
            R.color.color_text_blue,
            R.color.color_text_blue
        )
    }

    private fun initRecyclerFavorite() {
        favoriteAdapter = FavoriteAdapter(context!!)
        addDataForAdapter()
        recyclerFavorite.adapter = favoriteAdapter
        favoriteAdapter.registerOnItemClickListener(this)
    }

    private fun addDataForAdapter() {
        getListIdItem()
        viewModel.getListLego().observe(this, Observer {
            progressLoadingFavorite.visibility = View.INVISIBLE
            favoriteAdapter.clearData()
            favoriteAdapter.addData(it as MutableList<Lego>)
        })
    }

    private fun getListIdItem() {
        viewModel.getListIdItem().observe(this, Observer {
            if(!it.isNullOrEmpty()){
                viewModel.getLegoById(it as ArrayList<String>)
            }
            else{
                favoriteAdapter.clearData()
                constraintMiddleFavorite.visibility = View.VISIBLE
                progressLoadingFavorite.visibility = View.INVISIBLE
            }
        })
    }

    companion object {
        fun getInstance() = FavoriteFragment()
    }

}
