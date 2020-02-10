package com.example.kotlin.screen.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.model.Lego
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavoriteViewModel : ViewModel() {
    private val listIdItem = MutableLiveData<List<String>>()
    private val listLegos = MutableLiveData<List<Lego>>()

    private val uid = FirebaseAuth.getInstance().uid

    private val firebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }

    private val databaseReference by lazy {
        firebaseDatabase.reference
    }

    fun getListIdItem() = listIdItem

    fun getListLego() = listLegos

    fun getIdItemFavorite() {
        val listId = ArrayList<String>()
        databaseReference.database.getReference("Marked").child(uid!!)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.exists()) {
                            p0.children.map {
                                val id = it.key.toString()
                                listId.add(id)
                            }
                        }
                        listIdItem.value = listId
                        databaseReference.removeEventListener(this)
                    }
                }
            )
    }

    fun getLegoById(listId: ArrayList<String>) {
        val listLego = ArrayList<Lego>()
        databaseReference.database.getReference("Item")
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.exists()) {
                            for (i in listId.indices) {
                                val id = p0.child(listId[i]).child("id").value.toString()
                                val author = p0.child(listId[i]).child("author").value.toString()
                                val cate_id = p0.child(listId[i]).child("cate_id").value.toString()
                                val detail = p0.child(listId[i]).child("detail").value.toString()
                                val image = p0.child(listId[i]).child("image").value.toString()
                                val name = p0.child(listId[i]).child("name").value.toString()
                                val price = p0.child(listId[i]).child("price").value.toString()
                                val quantity =
                                    p0.child(listId[i]).child("quantity").value.toString()
                                val sale = p0.child(listId[i]).child("sale").value.toString()
                                val sale_id = p0.child(listId[i]).child("sale_id").value.toString()
                                val rate = p0.child(listId[i]).child("rate").value.toString()
                                val remain = (p0.child(listId[i]).child("remain").value.toString()).toInt()
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
                                listLego.add(lego)
                            }
                        }
                        listLegos.value = listLego
                        databaseReference.removeEventListener(this)
                    }
                }
            )
    }

}