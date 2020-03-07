package com.example.onlinestore.views

import com.example.onlinestore.R
import com.example.onlinestore.models.SelectedCategoryItem
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.offer_category_add_new_offer_row.view.*

class AddNewOfferCategoryItemRow(val selectedCategoryItem: SelectedCategoryItem): Item<GroupieViewHolder>(){
    override fun getLayout(): Int = R.layout.offer_category_add_new_offer_row

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textView_category_title_add_new_offer_row.text = selectedCategoryItem.title
        viewHolder.itemView.textView_category_type_add_new_offer_row.text = selectedCategoryItem.type
    }

}