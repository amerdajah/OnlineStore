package com.example.onlinestore.views

import android.content.Context
import com.bumptech.glide.Glide
import com.example.onlinestore.R
import com.example.onlinestore.models.Offer
import com.example.onlinestore.models.User
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.offer_row.view.*

class OfferItemRow(private val context: Context, private val offer: Offer, private val seller: User): Item<GroupieViewHolder>() {

    val offerItem = offer

    override fun getLayout(): Int {
        return R.layout.offer_row
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textView_title_offer_row.text = offer.title
        viewHolder.itemView.textView_description_offer_row.text = offer.description
        viewHolder.itemView.textView_time_offer_row.text = offer.timeSnap
        viewHolder.itemView.textView_price_offerRow.text = offer.price
        Glide
            .with(context)
            .load(offer.offerImage)
            .into(viewHolder.itemView.imageView_circle_offer_row)
        Glide
            .with(context)
            .load(seller.profileImageUri)
            .into(viewHolder.itemView.imageView_sellerPic_offer_row)
    }
}