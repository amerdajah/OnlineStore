@file:Suppress("DEPRECATION")

package com.example.onlinestore.offers

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.onlinestore.R
import com.example.onlinestore.models.Offer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add_new_offer.*
import java.text.SimpleDateFormat
import java.util.*

class AddNewOfferActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog
    private var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_offer)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("please wait, try to post new offer...")



        button_select_photo_newoffer.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        button_add_offer.setOnClickListener {
            progressDialog.show()
            uploadImageToFireBaseStorage()
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun addNewOffer(profileImageUri: String) {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val price = editText_price_newOffer.text.toString()
        val currentDate = sdf.format(Date())
        val sellerId = FirebaseAuth.getInstance().uid
        val title = editText_offer_title.text.toString()
        val ref = FirebaseDatabase.getInstance().getReference("/offers/$title")
        val offer = Offer(
            sellerId!!,
            title,
            editText_offer_description.text.toString(),
            profileImageUri,
            currentDate,
            price
        )
        ref.setValue(offer)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val intent = Intent(this, LatestOffersActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadImageToFireBaseStorage() {
        if (selectedPhotoUri == null) {
            addNewOffer("https://firebasestorage.googleapis.com/v0/b/fir-b3607.appspot.com/o/images%2FdefaultImage.png?alt=media&token=5b3b337b-c8a0-4851-89a6-fb6468840442")
        } else {
            val fileName = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/offersImages/${fileName}")

            ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    //Toast.makeText(this, "Successfully uploaded the image", Toast.LENGTH_LONG).show()
                    ref.downloadUrl.addOnSuccessListener {
                        addNewOffer(it.toString())
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null){
                    selectedPhotoUri = data.data!!
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
                    imageView_circle_newoffer.setImageBitmap(bitmap)
                    button_select_photo_newoffer.alpha = 0f
                }
            }
        }
    }
}
