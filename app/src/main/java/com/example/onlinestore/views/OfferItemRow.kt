package com.example.onlinestore.views

import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestore.R
import com.example.onlinestore.models.Offer
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.offer_row.view.*

class OfferItemRow(private val offer: Offer): Item<GroupieViewHolder>() {

    val offerItem = offer

    override fun getLayout(): Int {
        return R.layout.offer_row
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textView_title_offer_row.text = offer.title
        viewHolder.itemView.textView_description_offer_row.text = offer.description
        viewHolder.itemView.textView_time_offer_row.text = offer.timeSnap
        viewHolder.itemView.textView_price_offerRow.text = offer.price
        Picasso.get().load(offer.offerImage).into(viewHolder.itemView.imageView_circle_offer_row)    }
}