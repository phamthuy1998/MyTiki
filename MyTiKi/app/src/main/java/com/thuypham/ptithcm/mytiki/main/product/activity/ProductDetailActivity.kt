package com.thuypham.ptithcm.mytiki.main.product.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.thuypham.ptithcm.mytiki.R
import com.thuypham.ptithcm.mytiki.help.PhysicsConstants
import com.thuypham.ptithcm.mytiki.main.product.model.Product
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.item_product.view.*

class ProductDetailActivity : AppCompatActivity() {

    lateinit var mDatabaseReference: DatabaseReference
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        getData()
    }

    private fun setData(product: Product) {
        //sset image view product
        Glide.with(applicationContext)
                .load(product.image)
                .into(iv_product_detail)

        tv_name_product_detail.text= product.name
        val strPercent= product.sale.toString()+ "%"
        tv_discount_percent.text= strPercent
        var price= (product.price?.times(product.sale))?.div(100)

        //price discount
        tv_price_discount_product_detail.text= price.toString()
        //price
        tv_price_product_detail.text= product.price.toString()


    }

    private fun getData() {
        val id_product = intent.getStringExtra("id_product")
        if(!id_product.isEmpty())
         getProductById(id_product)
    }

    private fun getProductById( id: String) {
         var product: Product
        println("vo toi day luon nhi")
        mDatabase = FirebaseDatabase.getInstance()
        val query = mDatabase!!
                .reference
                .child(PhysicsConstants.PRODUCT)
                .child(id)

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(ds: DataSnapshot) {
                if (ds.exists()) {
                    println("co vo day lay thong tin k")
                    val name = ds.child(PhysicsConstants.NAME_PRODUCT).value as String
                    val price = ds.child(PhysicsConstants.PRICE_PRODUCT).value as Long
                    val image = ds.child(PhysicsConstants.IMAGE_PRODUCT).value as String
                    val infor = ds.child(PhysicsConstants.INFOR_PRODUCT).value as String
                    val product_count = ds.child(PhysicsConstants.PRODUCT_COUNT).value as Long
                    val id_category = ds.child(PhysicsConstants.ID_CATEGORY_PRODUCT).value as String
                    val sale = ds.child(PhysicsConstants.PRODUCT_SALE).value as Long

                    println("name of product : $name")
                    product = Product(id, name, price, image, infor, product_count, id_category, sale)
                    setData(product)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("lay 1 sp k thanh cong")
            }
        }
        query.addValueEventListener(valueEventListener)

    }


    fun onClickQuiteProduct(view: View) {
        finish()
    }
}
