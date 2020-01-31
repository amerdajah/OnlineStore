package com.example.onlinestore.views

import com.example.onlinestore.R
import com.example.onlinestore.models.Offer
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.offer_row.view.*

class OfferItemRow(private val offer: Offer): Item<ViewHolder>() {

    val offerItem = offer

    override fun bind(p0: ViewHolder, p1: Int) {
        p0.itemView.textView_title_offer_row.text = offer.title
        p0.itemView.textView_description_offer_row.text = offer.description
        p0.itemView.textView_time_offer_row.text = offer.timeSnap
        p0.itemView.textView_price_offerRow.text = offer.price
        Picasso.get().load(offer.offerImage).into(p0.itemView.imageView_circle_offer_row)
    }

    override fun getLayout(): Int {
        return R.layout.offer_row
    }
}