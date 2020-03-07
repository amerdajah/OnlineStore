package com.example.onlinestore.views


import com.example.onlinestore.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.sub_category_item_row.view.*

class SubCategoryItemRow(val title: String): Item<GroupieViewHolder>() {


    override fun getLayout(): Int {
        return R.layout.sub_category_item_row
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.textView_subCategory_item_row.text = title
    }
}