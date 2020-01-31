package com.example.onlinestore.offers

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.onlinestore.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_category_offer_type.*
import kotlinx.android.synthetic.main.activity_latest_offers.*

class CategoryOfferType : AppCompatActivity() {

    private val adapter = GroupAdapter<ViewHolder>()
    lateinit var progressDialog: ProgressDialog
    //private lateinit var arrayList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_offer_type)

        title = "Latest Offers"
        recyclerView_mainCategory.adapter = adapter
        recyclerView_mainCategory.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("please wait...")

        val myArray = arrayOf("Cars and Bikes", "Mobile - Tablet", "Video Games & Consoles",
            "Electronics - Appliances", "Real Estate for Sale", "Real Estate for Rent", "Furniture - Decor", "Women's Fashion",
            "Men's Fashion", "Baby - Kids", "Pets")
        val carsArray = arrayOf("Cars For Sale", "Car Rental", "Motorcycle", "Car Plates Number", "Car Accessories",
            "Wheel and Rims", "Trucks - Heavy Machinery", "Boat, Yacht, and Jet Ski", "Junk Cars - Other Vehicles")
        val mobileArray = arrayOf("Mobiles", "Tablet", "VIP Phone Numbers", "Mobile Tablet Accessories",
            "Mobile - Tablet Spare Parts", "Others - Mobile - Tablet")
        val videoGamesArray = arrayOf("Consoles", "Video Games", "Accessories - Replacement Parts",
            "Gaming Cards", "Accounts and Characters", "Action Figures", "Others - Gaming")
        val electronicsArray = arrayOf("Laptops - Computers", "TVs - Screens", "Speakers - Amplifiers", "Stereo - Radios",
            "Receivers - DVD Players", "Modems - Routers", "Cameras - Photography", "Air Conditioners - Fans", "Refrigerators - Freezers",
            "Washers - Dishwashers", "Ovens - Microwaves", "Water Coolers and Filters")
        val realEstateSaleArray = arrayOf("Apartment for sale", "Villa - Place for sale", "Land for Sale",
            "Commercial for Sale", "Whole Building for Sale", "Chalets - Summerhouses for Sale", "Foreign Real Estate for Sale")
        val realEstateRentArray = arrayOf("Apartment for Rent", "Villa - Place for Rent", "Land for Rent",
            "Commercial for Rent", "Whole Building for Rent", "Chalets - Summerhouses for Rent", "Foreign Real Estate for Rent")
        val furnitureArray = arrayOf("Furniture", "Home Decor - Furnishings", "Kitchen Tools - Hosting",
            "Houseware", "Other Furniture - Decor")
        val womenFashionArray = arrayOf("Clothes", "Women Shoes", "Watches", "Accessories = Jewelry", "Bags",
            "Perfumes - Incense", "Personal Care Devices", "Beauty Cosmetics", "Personal Health Care", "Others - Women's Fashion")
        val menFashionArray = arrayOf("Menswear", "Men's Shoes", "Men's Watches", "Men's Accessories",
            "Personal Care Devices", "Perfume - Incense for men", "Others = Men's Fashion")
        val babyArray = arrayOf("Kids Furniture", "Strollers - Cars Seats", "Kids Clothing", "Toys-Games", "Other - Baby-kids")
        val petsArray = arrayOf("Cats", "Birds", "Pigeons", "Sheep", "Animal Food", "Accessories", "Other Animal")
    }
}
