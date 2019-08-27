package com.thuypham.ptithcm.mytiki.main.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thuypham.ptithcm.mytiki.R
import com.thuypham.ptithcm.mytiki.main.fragment.category.model.Category
import com.thuypham.ptithcm.mytiki.main.fragment.home.fragment.HomeFragment
import com.thuypham.ptithcm.mytiki.main.product.model.Product
import kotlinx.android.synthetic.main.item_product.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class ProductAdapter(private var items: ArrayList<Product>, private val context: HomeFragment) : RecyclerView.Adapter<BaseItem>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseItem {
        val view = LayoutInflater
                .from(viewGroup.context)
                .inflate(R.layout.item_product, viewGroup, false);
        return ProductViewholder(view)
    }

    inner class ProductViewholder(view: View) : BaseItem(view) {
        override fun bind(position: Int) {
            val product = items[position]

            itemView.tv_name_product.text = product.name

            val df = DecimalFormat("#,###,###")
            df.roundingMode = RoundingMode.CEILING

            // set price for product
            val price = df.format(product.price) + " đ"
            itemView.tv_price_product.text = price
            val sale= "-" + product.sale .toString()+"%"
            itemView.tv_sale_product.text= sale
            println("gia sp : $price")
            println("name sp : ${product.name}")

            //sset image view category
            Glide.with(itemView)
                    .load(product.image)
                    .into(itemView.iv_product)
        }

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

}