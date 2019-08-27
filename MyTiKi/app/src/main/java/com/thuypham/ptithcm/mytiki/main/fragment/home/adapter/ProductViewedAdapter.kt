package com.thuypham.ptithcm.mytiki.main.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thuypham.ptithcm.mytiki.R
import com.thuypham.ptithcm.mytiki.main.fragment.home.fragment.HomeFragment
import com.thuypham.ptithcm.mytiki.main.product.model.Product
import kotlinx.android.synthetic.main.item_product_sale.view.*
import kotlinx.android.synthetic.main.item_product_viewed.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class ProductViewedAdapter (private var items: ArrayList<Product>, private val context: HomeFragment) : RecyclerView.Adapter<BaseItem>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseItem {
        val view = LayoutInflater
                .from(viewGroup.context)
                .inflate(R.layout.item_product_viewed, viewGroup, false);
        return ProductSaleViewholder(view)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseItem, position: Int) {
        holder.bind(position)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    inner class ProductSaleViewholder(view: View) : BaseItem(view) {
        override fun bind(position: Int) {
            val product = items[position]

            //sset image view product
            Glide.with(itemView)
                    .load(product.image)
                    .into(itemView.iv_image_product_viewed)

            // name product viewed
            itemView.tv_name_product_viewed.text = product.name

            // format price viewed
            val df = DecimalFormat("#,###,###")
            df.roundingMode = RoundingMode.CEILING

            // set price for product
            val price = df.format(product.price) + " đ"
            itemView.tv_price_product_viewed.text = price


        }

    }

}