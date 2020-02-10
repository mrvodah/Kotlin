package com.example.kotlin.screen.home


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentHomeBinding
import com.example.kotlin.listener.ListenShowChatBot
import com.example.kotlin.screen.base.recyclerview.OnItemClickListener
import com.example.kotlin.model.Lego
import com.example.kotlin.screen.category_detail.CategoryDetailFragment
import com.example.kotlin.screen.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(),
    OnItemClickListener<Lego>, HomeNavigator {
    override fun directToLogin() {
        startActivity(LoginActivity.getIntent(context!!))
        activity?.finish()
    }

    override fun onItemClick(item: Lego, position: Int) {
    }

    private lateinit var viewDataBinding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var carsAdapter: CarsAdapter

    private lateinit var listenShowChatBot: ListenShowChatBot

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenShowChatBot = context as ListenShowChatBot
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.setNavigator(this)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initRecyclerView()
        getAllLego()
        viewDataBinding.recyclerLegoCars.adapter = carsAdapter
        registerEvents()
    }

    private fun registerEvents() {
        textMoreCar.setOnClickListener {
            replaceFragment(CAR_KEY)
        }
    }

    private fun replaceFragment(type: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frameContainer, CategoryDetailFragment.getInstance(type))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun getAllLego() {
        viewModel.getCars().observe(this, Observer {
            carsAdapter.clearData()
            carsAdapter.addData(it as MutableList<Lego>)
        })
    }

    private fun initRecyclerView() {
        carsAdapter = CarsAdapter(context!!)
        carsAdapter.registerOnItemClickListener(this)
    }

    private fun initView() {
        viewModel.getQuantity().observe(this, Observer {
            if(it > 0){
                textNotificationHome.text = it.toString()
                textNotificationHome.visibility = View.VISIBLE
            }
            else{
                textNotificationHome.visibility = View.INVISIBLE
            }
        })
    }

    companion object {
        fun getInstance() = HomeFragment()
        const val CAR_KEY = "Car"
    }

}
