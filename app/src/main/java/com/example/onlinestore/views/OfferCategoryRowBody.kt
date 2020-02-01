package com.example.onlinestore.views

import com.example.onlinestore.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.offer_category_row_body.view.*

class OfferCategoryRowBody(private val childTitle: String): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textView_expandable_body_subtitle.text = childTitle
    }

    override fun getLayout(): Int = R.layout.offer_category_row_body
}