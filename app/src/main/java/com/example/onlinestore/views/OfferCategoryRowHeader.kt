package com.example.onlinestore.views

import android.graphics.Typeface
import com.example.onlinestore.R
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.offer_category_row_header.*
import kotlinx.android.synthetic.main.offer_category_row_header.view.*

class OfferCategoryRowHeader(private val headerTitle: String): Item(), ExpandableItem {

    private lateinit var expandableGroup: ExpandableGroup


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.textView_expandable_header_title.text = headerTitle
        viewHolder.item_expandable_header_icon.setImageResource(getRotatedIconResId(viewHolder))

        viewHolder.item_expandable_header_root.setOnClickListener {
            expandableGroup.onToggleExpanded()
            viewHolder.item_expandable_header_icon.setImageResource(getRotatedIconResId(viewHolder))
        }
    }

    override fun getLayout(): Int = R.layout.offer_category_row_header

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    private fun getRotatedIconResId(viewHolder: GroupieViewHolder) =
        if (expandableGroup.isExpanded) {
            viewHolder.itemView.textView_expandable_header_title.typeface = Typeface.DEFAULT_BOLD
            R.drawable.ic_keyboard_arrow_up_black_24dp
        }else {
            viewHolder.itemView.textView_expandable_header_title.typeface = Typeface.DEFAULT
            R.drawable.ic_keyboard_arrow_down_black_24dp
        }

}