@file:Suppress("DEPRECATION")

package com.example.onlinestore.offers

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestore.R
import com.example.onlinestore.loginregister.LoginActivity
import com.example.onlinestore.models.Offer
import com.example.onlinestore.models.User
import com.example.onlinestore.views.OfferItemRow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_latest_offers.*

class LatestOffersActivity : AppCompatActivity() {


    private val adapter = GroupAdapter<GroupieViewHolder>()
    lateinit var progressDialog: ProgressDialog
    private var uid: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_offers)

        title = "Latest Offers"
        recycleView_newOffer.adapter = adapter
        recycleView_newOffer.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("please wait...")

        verifyUserIsLoggedIn()

        adapter.setOnItemClickListener { item, _ ->
            progressDialog.show()
            val offerItemRow = item as OfferItemRow
            goToViewTheOffer(item.offerItem.seller, offerItemRow, this)

        }
    }

    private fun goToViewTheOffer(sellerId: String, offerItemRow: OfferItemRow, context: Context){
        val ref = FirebaseDatabase.getInstance().getReference("/users/$sellerId")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val user = p0.getValue(User::class.java)?: return
                val intent = Intent(context, ViewOfferActivity::class.java)
                intent.putExtra("offerItem", offerItemRow.offerItem)
                intent.putExtra("sellerUser", user)
                progressDialog.dismiss()
                startActivity(intent)
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })

    }

    private fun verifyUserIsLoggedIn() {
        uid = FirebaseAuth.getInstance().uid
        if (uid == null){
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }else {
            progressDialog.show()
            goOnline()
            fetchOffers()
        }
    }

    private fun goOnline(){
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.child(uid+"").child("status").setValue("Online")
    }

    private fun goOffline(){
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.child(uid+"").child("status").setValue("Offline")
    }

    private fun fetchOffers() {
        val ref = FirebaseDatabase.getInstance().getReference("/offers")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    val offer: Offer = it.getValue(Offer::class.java)!!
                    adapter.add(OfferItemRow(offer))
                }
                progressDialog.dismiss()
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }

    override fun onStop() {
        super.onStop()
        goOffline()
    }

    override fun onPostResume() {
        super.onPostResume()
        goOnline()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_new_message -> {
                val intent = Intent(this, AddNewOfferActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_sign_out -> {
                goOffline()
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

