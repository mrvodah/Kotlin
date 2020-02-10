package com.example.kotlin.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.model.Lego
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeViewModel: ViewModel() {
    private lateinit var homeNavigator: HomeNavigator
    private var livedata_cars = MutableLiveData<List<Lego>>()
    private var quantity = MutableLiveData<Int>()

    private val uid = FirebaseAuth.getInstance().uid

    private val firebaseDatabase: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }

    private val databaseReference: DatabaseReference by lazy {
        firebaseDatabase.reference
    }

    fun setNavigator(homeNavigator: HomeNavigator) {
        this.homeNavigator = homeNavigator
    }

    fun getQuantity() = quantity

    fun quantityProductInCart() {
        if(uid == null){
            homeNavigator.directToLogin()
        }
        else{
            databaseReference.database.getReference("Order").child(uid)
                .addValueEventListener(
                    object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            var notification = 0
                            if(p0.exists()){
                                p0.children.map {
                                    notification =
                                        notification.plus((it.child("count").value.toString()).toInt())
                                }
                                quantity.value = notification
                            } else{
                                quantity.value = 0
                            }
                        }

                    }
                )
        }
    }

    fun readDataCars() {
        val cars = ArrayList<Lego>()
        databaseReference.database.getReference("Item").limitToFirst(5)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if(p0.exists()){
                            p0.children.map {
                                val id = it.child("id").value.toString()
                                val author = it.child("author").value.toString()
                                val cate_id = it.child("cate_id").value.toString()
                                val detail = it.child("detail").value.toString()
                                val image = it.child("image").value.toString()
                                val name = it.child("name").value.toString()
                                val price = it.child("price").value.toString()
                                val quantity = it.child("quantity").value.toString()
                                val sale = it.child("sale").value.toString()
                                val sale_id = it.child("sale_id").value.toString()
                                val rate = it.child("rate").value.toString()
                                val remain = (it.child("remain").value.toString()).toInt()
                                val lego = Lego(
                                    id,
                                    cate_id,
                                    name,
                                    author,
                                    quantity,
                                    price,
                                    image,
                                    detail,
                                    rate,
                                    remain,
                                    sale,
                                    sale_id
                                )
                                cars.add(lego)
                            }
                            livedata_cars.value = cars
                            databaseReference.removeEventListener(this)
                        }
                    }
                }
            )
    }

    fun getCars() = livedata_cars

}