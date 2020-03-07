@file:Suppress("DEPRECATION")

package com.example.onlinestore.offers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinestore.R
import com.example.onlinestore.models.SelectedCategoryItem
import com.example.onlinestore.views.OfferCategoryRowBody
import com.example.onlinestore.views.OfferCategoryRowHeader
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.activity_category_offer_type.*


class CategoryOfferType : AppCompatActivity() {

    private val cashForLoadArrays = ArrayList<OfferCategoryRowBody>()
    private  var witchParent: String? = null
    private val returnedResult = ArrayList<SelectedCategoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_offer_type)

        val witch = intent.getStringExtra("amer")
        if (witch != null)
            witchParent = witch

        title = "Choose The Type"
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            spanCount = 3
        }
        recyclerView_mainCategory.apply {
            layoutManager = GridLayoutManager(this@CategoryOfferType, mAdapter.spanCount, LinearLayoutManager.VERTICAL, false).apply {
                spanSizeLookup = mAdapter.spanSizeLookup
            }
            //addItemDecoration(DividerItemDecoration(this@CategoryOfferType, DividerItemDecoration.VERTICAL))
            adapter = mAdapter
        }

        resources.getStringArray(R.array.main_array).forEach {
            ExpandableGroup(OfferCategoryRowHeader(it), true).apply {
                add(Section(getHeadersChildren(it)))
                isExpanded = false
                if (witchParent != null && it == witchParent)
                    isExpanded = true
                mAdapter.add(this)
            }
        }
        mAdapter.setOnItemClickListener { item, _ ->
            val it = item as OfferCategoryRowBody
            returnedResult.add(SelectedCategoryItem("Category", it.parentTitle))
            returnedResult.add(SelectedCategoryItem("Sub Category", it.childTitle))
            val intent= Intent(this, SubCategory::class.java)
            intent.putExtra("child", it.childTitle)
            intent.putExtra("parent", it.parentTitle)
            startActivityForResult(intent, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    data.getParcelableArrayListExtra<SelectedCategoryItem>("result")!!.forEach {
                        returnedResult.add(it)
                    }
                    val returnIntent = Intent()
                        .putExtra("result", returnedResult)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }
            }
        }
    }

    private fun getHeadersChildren(arrayName: String): ArrayList<OfferCategoryRowBody>{
        cashForLoadArrays.clear()
        var childPosition = 0
         when (arrayName){
            resources.getStringArray(R.array.main_array)[0]-> {
                resources.getStringArray(R.array.cars_array).forEach {
                    cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[0], childPosition))
                    childPosition++
                }
            }
             resources.getStringArray(R.array.main_array)[1] -> {
                 resources.getStringArray(R.array.mobile_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[1], childPosition))
                     childPosition++
                 }
             }
             resources.getStringArray(R.array.main_array)[2]-> {
                 resources.getStringArray(R.array.videoGames_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[2], childPosition))
                     childPosition++
                 }
             }
             resources.getStringArray(R.array.main_array)[3] -> {
                 resources.getStringArray(R.array.electronics_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[3], childPosition))
                     childPosition++
                 }
             }
             resources.getStringArray(R.array.main_array)[4] -> {
                 resources.getStringArray(R.array.realEstateSale_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[4], childPosition))
                     childPosition++
                 }
             }
             resources.getStringArray(R.array.main_array)[5] -> {
                 resources.getStringArray(R.array.realEstateRent_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[5], childPosition))
                     childPosition++
                 }
             }
             resources.getStringArray(R.array.main_array)[6] -> {
                 resources.getStringArray(R.array.furniture_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[6], childPosition))
                     childPosition++
                 }
             }
             resources.getStringArray(R.array.main_array)[7] -> {
                 resources.getStringArray(R.array.womenFashion_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[7], childPosition))
                     childPosition++
                 }
             }
             resources.getStringArray(R.array.main_array)[8] -> {
                 resources.getStringArray(R.array.menFashion_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[8], childPosition))
                     childPosition++
                 }
             }
             resources.getStringArray(R.array.main_array)[9] -> {
                 resources.getStringArray(R.array.baby_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[9], childPosition))
                     childPosition++
                 }
             }
             resources.getStringArray(R.array.main_array)[10] -> {
                 resources.getStringArray(R.array.pets_array).forEach {
                     cashForLoadArrays.add(OfferCategoryRowBody(it, resources.getStringArray(R.array.main_array)[10], childPosition))
                     childPosition++
                 }
             }
        }
        return cashForLoadArrays
    }
}
