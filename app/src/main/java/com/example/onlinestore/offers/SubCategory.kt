package com.example.onlinestore.offers

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinestore.R
import com.example.onlinestore.models.SelectedCategoryItem
import com.example.onlinestore.views.SubCategoryItemRow
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_sub_category.*

class SubCategory : AppCompatActivity() {

    private val returnedResult = ArrayList<SelectedCategoryItem>()
    private var touchCounter = 0
    private val mAdapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var comingChild: String
    private lateinit var comingParent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)

        comingChild = intent.getStringExtra("child")!!
        comingParent = intent.getStringExtra("parent")!!
        recyclerView_sucCategory.apply {
            layoutManager = LinearLayoutManager(this@SubCategory)
            adapter = mAdapter
        }
        putTheChosenArray(comingChild)
        val arrayId = when (comingChild) {
            resources.getStringArray(R.array.cars_array)[0] , resources.getStringArray(R.array.cars_array)[1] -> R.array.carSaleSteps
            resources.getStringArray(R.array.cars_array)[2] -> R.array.motorcycleSteps
            resources.getStringArray(R.array.cars_array)[5] -> R.array.wheelSizeSteps
            resources.getStringArray(R.array.cars_array)[6] -> R.array.trucksSaleSteps
            resources.getStringArray(R.array.cars_array)[7] -> R.array.boatsSaleSteps
            else -> 0
        }
        if (arrayId != 0)
            textView_subCategory_title.text = resources.getStringArray(arrayId)[0]
        mAdapter.setOnItemClickListener { item, _ ->
            touchCounter++
            val myItemRow = item as SubCategoryItemRow
            when (comingChild) {
                resources.getStringArray(R.array.cars_array)[0] -> {
                    when (touchCounter) {
                        1 -> {
                            putTheChosenCarType(myItemRow.title)
                            saveAndTitle(myItemRow.title)
                        }
                        2-> {
                            val j: Intj = getTheArray("yearMade")
                            saveAndTitle(myItemRow.title)
                        }
                        3 -> {
                            getTheArray("usedOrNew")
                            saveAndTitle(myItemRow.title)
                        }
                        4 -> {
                            getTheArray("carMiles")
                            saveAndTitle(myItemRow.title)
                        }
                        5 -> {
                            getTheArray("automaticOrManual")
                            saveAndTitle(myItemRow.title)
                        }
                        6 -> {
                            getTheArray("fuel")
                            saveAndTitle(myItemRow.title)
                        }
                        7 -> {
                            getTheArray("payment")
                            saveAndTitle(myItemRow.title)
                        }
                        8 -> {
                            returnedResult.add(SelectedCategoryItem(textView_subCategory_title.text.toString(),myItemRow.title))
                            val returnIntent = Intent()
                                .putExtra("result", returnedResult)
                            setResult(Activity.RESULT_OK, returnIntent)
                            finish()
                        }
                    }
                }
                resources.getStringArray(R.array.cars_array)[1] -> {
                    when (touchCounter) {
                        1 -> {
                            putTheChosenCarType(myItemRow.title)
                            saveAndTitle(myItemRow.title)
                        }
                        2-> {
                            getTheArray("yearMade")
                            saveAndTitle(myItemRow.title)
                        }
                        3 -> {
                            getTheArray("automaticOrManual")
                            saveAndTitle(myItemRow.title)
                        }
                        4 -> {
                            getTheArray("fuel")
                            saveAndTitle(myItemRow.title)
                        }
                        5 -> {
                            getTheArray("rentTime")
                            saveAndTitle(myItemRow.title)
                        }
                        6 -> {
                            returnedResult.add(SelectedCategoryItem(textView_subCategory_title.text.toString(),myItemRow.title))
                            val returnIntent = Intent()
                                .putExtra("result", returnedResult)
                            setResult(Activity.RESULT_OK, returnIntent)
                            finish()
                        }
                    }
                }
                resources.getStringArray(R.array.cars_array)[2] -> {
                    when (touchCounter) {
                        1-> {
                            getTheArray("yearMade")
                            saveAndTitle(myItemRow.title)
                        }
                        2 -> {
                            getTheArray("usedOrNew")
                            saveAndTitle(myItemRow.title)
                        }
                        3 -> {
                            getTheArray("carMiles")
                            saveAndTitle(myItemRow.title)
                        }
                        4 -> {
                            getTheArray("motorcycleEngineSize")
                            saveAndTitle(myItemRow.title)
                        }
                        5 -> {
                            getTheArray("payment")
                            saveAndTitle(myItemRow.title)
                        }
                        6 -> {
                            returnedResult.add(SelectedCategoryItem(textView_subCategory_title.text.toString(),myItemRow.title))
                            val returnIntent = Intent()
                                .putExtra("result", returnedResult)
                            setResult(Activity.RESULT_OK, returnIntent)
                            finish()
                        }
                    }
                }
                resources.getStringArray(R.array.cars_array)[5] -> {
                    returnedResult.add(SelectedCategoryItem(textView_subCategory_title.text.toString(),myItemRow.title))
                    val returnIntent = Intent()
                        .putExtra("result", returnedResult)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }
                resources.getStringArray(R.array.cars_array)[6] -> {
                    when (touchCounter)  {
                        1 -> {
                            getTheArray("usedOrNew")
                            saveAndTitle(myItemRow.title)
                        }
                        2 -> {
                            returnedResult.add(SelectedCategoryItem(textView_subCategory_title.text.toString(),myItemRow.title))
                            val returnIntent = Intent()
                                .putExtra("result", returnedResult)
                            setResult(Activity.RESULT_OK, returnIntent)
                            finish()
                        }
                    }
                }
                resources.getStringArray(R.array.cars_array)[7] -> {
                    when (touchCounter)  {
                        1 -> {
                            getTheArray("usedOrNew")
                            saveAndTitle(myItemRow.title)
                        }
                        3 -> {
                            returnedResult.add(SelectedCategoryItem(textView_subCategory_title.text.toString(),myItemRow.title))
                            val returnIntent = Intent()
                                .putExtra("result", returnedResult)
                            setResult(Activity.RESULT_OK, returnIntent)
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun getTheArray(title: String): Int {
        mAdapter.clear()
        return when (title) {
            "payment" -> R.array.paymentMethod
            "rentTime" -> R.array.rentTime
            "motorcycleEngineSize" -> R.array.motorcycleEngine
            "fuel" -> R.array.powerEngineType
            "automaticOrManual" -> R.array.automaticOrManual
            "carMiles" -> R.array.carMiles
            "usedOrNew" -> R.array.usedOrNew
            "yearMade" -> R.array.yearMade
            else -> 0
        }
    }

    private fun putTheChosenCarType(title: String) {
        mAdapter.clear()
        val arrayId = when (title) {
            resources.getStringArray(R.array.carType)[0] -> R.array.carTypeHyundai
            resources.getStringArray(R.array.carType)[1] -> R.array.carTypeToyota
            resources.getStringArray(R.array.carType)[2] -> R.array.carTypeKia
            resources.getStringArray(R.array.carType)[3] -> R.array.carTypeBMW
            resources.getStringArray(R.array.carType)[4] -> R.array.carTypeMercedesBenz
            resources.getStringArray(R.array.carType)[5] -> R.array.carTypeAstonMartin
            resources.getStringArray(R.array.carType)[6] -> R.array.carTypeAudi
            resources.getStringArray(R.array.carType)[7] -> R.array.carTypeIsuzu
            resources.getStringArray(R.array.carType)[8] -> R.array.carTypeOpel
            resources.getStringArray(R.array.carType)[9] -> R.array.carTypePorsche
            resources.getStringArray(R.array.carType)[10] -> R.array.carTypePeugeot
            resources.getStringArray(R.array.carType)[11] -> R.array.carTypeJaguar
            resources.getStringArray(R.array.carType)[12] -> R.array.carTypeJeep
            else -> return
        }
        resources.getStringArray(arrayId).forEach {
            mAdapter.add(SubCategoryItemRow(it))
        }
    }

    private fun putTheChosenArray(title: String) {
        mAdapter.clear()
        val arrayId = when (title) {
            resources.getStringArray(R.array.cars_array)[0] , resources.getStringArray(R.array.cars_array)[1] ->
                R.array.carType
            resources.getStringArray(R.array.cars_array)[2] -> R.array.motorcycleMade
            resources.getStringArray(R.array.cars_array)[5] -> R.array.wheelSize
            resources.getStringArray(R.array.cars_array)[6] -> R.array.heavyTrucks
            resources.getStringArray(R.array.cars_array)[7] -> R.array.boatsType
            else -> 0
        }
        if (arrayId != 0)
            resources.getStringArray(arrayId).forEach {
                mAdapter.add(SubCategoryItemRow(it))
            }
        else {
            val returnIntent = Intent()
                .putExtra("result", returnedResult)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
    private fun saveAndTitle(title: String) {
        val arrayId = when (comingChild) {
            resources.getStringArray(R.array.cars_array)[0] -> R.array.carSaleSteps
            resources.getStringArray(R.array.cars_array)[1] -> R.array.carRentSteps
            resources.getStringArray(R.array.cars_array)[2] -> R.array.motorcycleSteps
            resources.getStringArray(R.array.cars_array)[5] -> R.array.wheelSizeSteps
            resources.getStringArray(R.array.cars_array)[6] -> R.array.trucksSaleSteps
            resources.getStringArray(R.array.cars_array)[7] -> R.array.boatsSaleSteps
            else -> return
        }
        returnedResult.add(SelectedCategoryItem(textView_subCategory_title.text.toString(), title))
        textView_subCategory_title.text = resources.getStringArray(arrayId)[touchCounter]
    }
}