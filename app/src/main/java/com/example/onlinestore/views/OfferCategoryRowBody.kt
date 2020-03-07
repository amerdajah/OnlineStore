package com.example.onlinestore.views

import android.graphics.Color
import com.example.onlinestore.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.offer_category_row_body.view.*

class OfferCategoryRowBody(val childTitle: String, val parentTitle: String, val position: Int, val backgroundColor: Color?): Item() {

    constructor(childTitle: String, parentTitle: String) : this(childTitle, parentTitle,-1, null){

    }

    constructor(childTitle: String, parentTitle: String, position: Int) : this(childTitle, parentTitle, position, null)

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textView_expandable_body_subtitle.text = childTitle

    }

    override fun getLayout(): Int = R.layout.offer_category_row_body

    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 3
}