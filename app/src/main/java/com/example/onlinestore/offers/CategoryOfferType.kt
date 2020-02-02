@file:Suppress("DEPRECATION")

package com.example.onlinestore.offers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlinestore.R
import com.example.onlinestore.views.OfferCategoryRowBody
import com.example.onlinestore.views.OfferCategoryRowHeader
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.activity_category_offer_type.*
import kotlin.collections.ArrayList

class CategoryOfferType : AppCompatActivity() {

    private val cashForLoadArrays = ArrayList<OfferCategoryRowBody>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_offer_type)

        title = "Choose The Type"
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            spanCount = 3
        }
        recyclerView_mainCategory.apply {
            layoutManager = GridLayoutManager(this@CategoryOfferType, mAdapter.spanCount).apply {
                spanSizeLookup = mAdapter.spanSizeLookup
            }
            adapter = mAdapter
        }
        resources.getStringArray(R.array.main_array).forEach {
            ExpandableGroup(OfferCategoryRowHeader(it), true).apply {
                add(Section(getHeadersChildren(it)))
                isExpanded = false
                mAdapter.add(this)
            }
        }
        mAdapter.setOnItemClickListener { _, view ->
            view.setBackgroundColor(resources.getColor(R.color.colorAccent))
        }
    }

    private fun getHeadersChildren(arrayName: String): ArrayList<OfferCategoryRowBody>{
        cashForLoadArrays.clear()
         when (arrayName){
            "Cars and Bikes"-> {
                resources.getStringArray(R.array.cars_array).forEach {
                    cashForLoadArrays.add(OfferCategoryRowBody(it))
                }
            }
             "Mobile-Tablet" -> {
                 resources.getStringArray(R.array.mobile_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
             "Video Games-Consoles"-> {
                 resources.getStringArray(R.array.videoGames_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
             "Electronics-Appliances" -> {
                 resources.getStringArray(R.array.electronics_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
             "Real Estate for Sale" -> {
                 resources.getStringArray(R.array.realEstateSale_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
             "Real Estate for Rent" -> {
                 resources.getStringArray(R.array.realEstateRent_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
             "Furniture-Decor" -> {
                 resources.getStringArray(R.array.furniture_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
             "Women's Fashion" -> {
                 resources.getStringArray(R.array.womenFashion_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
             "Men's Fashion" -> {
                 resources.getStringArray(R.array.menFashion_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
             "Baby-Kids" -> {
                 resources.getStringArray(R.array.baby_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
             "Pets" -> {
                 resources.getStringArray(R.array.pets_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it))
                 }
             }
        }
        return cashForLoadArrays
    }
}
