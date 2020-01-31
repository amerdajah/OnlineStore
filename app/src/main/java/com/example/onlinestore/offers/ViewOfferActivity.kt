package com.example.onlinestore.offers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinestore.R
import com.example.onlinestore.models.Offer
import com.example.onlinestore.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_view_offer.*

class ViewOfferActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_offer)

        val offer: Offer? = intent.getParcelableExtra("offerItem")
        val sellerUser: User? = intent.getParcelableExtra("sellerUser")
        if(offer != null){
            textView_seller_name.text = sellerUser!!.userName
            Picasso.get().load(sellerUser.profileImageUri).into(imageView_seller_pic)
            textView_status.text = sellerUser.status
            textView_title_viewOffer.text = offer.title
            textView_offer_description_viewOffer.text = offer.description
            textView_price_viewOffer.text = offer.price
            Picasso.get().load(offer.offerImage).into(imageView_itemImage_viewOffer)
        }
    }

    private fun getTheUser(sellerId: String){
        var user: User
        val ref = FirebaseDatabase.getInstance().getReference("/users/$sellerId")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)?: return
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })

    }
}

