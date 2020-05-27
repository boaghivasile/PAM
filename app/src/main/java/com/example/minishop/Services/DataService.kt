package com.example.minishop.Services

import com.example.minishop.Model.Category
import com.example.minishop.Model.Product

object DataService {

    val categories = listOf(
        Category("SHIRTS", "shirtimage"),
        Category("HOODIES", "hoodieimage"),
        Category("HATS", "hatimage"),
        Category("DIGITAL GOODS", "digitalgoodsimage"),
        Category("SHIRTS", "shirtimage"),
        Category("HOODIES", "hoodieimage"),
        Category("HATS", "hatimage"),
        Category("DIGITAL GOODS", "digitalgoodsimage"),
        Category("SHIRTS", "shirtimage"),
        Category("HOODIES", "hoodieimage"),
        Category("HATS", "hatimage"),
        Category("DIGITAL GOODS", "digitalgoodsimage")
    )

    val hats = listOf(
        Product("Beanie", "$12", "hat1"),
        Product("Blue Hat", "$10", "hat2"),
        Product("White Hat", "$11", "hat3"),
        Product("Gray Hat", "$22", "hat4"),
        Product("Beanie", "$12", "hat1"),
        Product("Blue Hat", "$10", "hat2"),
        Product("White Hat", "$11", "hat3"),
        Product("Gray Hat", "$22", "hat4")
    )

    val hoodies = listOf(
        Product("Hoodie Gray", "$28", "hoodie1"),
        Product("Red Hoodie", "$38", "hoodie2"),
        Product("Gray Hoodie", "$20", "hoodie3"),
        Product("Black Hoodie", "$40", "hoodie4"),
        Product("Hoodie Gray", "$28", "hoodie1"),
        Product("Red Hoodie", "$38", "hoodie2"),
        Product("Gray Hoodie", "$20", "hoodie3"),
        Product("Black Hoodie", "$40", "hoodie4")
    )

    val shirts = listOf(
        Product("Black Shirt", "$25", "shirt1"),
        Product("White Shirt", "$16", "shirt2"),
        Product("Red Shirt", "$37", "shirt3"),
        Product("Gray Shirt", "$29", "shirt4"),
        Product("Shirt Black", "$19", "shirt4"),
        Product("Black Shirt", "$25", "shirt1"),
        Product("White Shirt", "$16", "shirt2"),
        Product("Red Shirt", "$37", "shirt3"),
        Product("Gray Shirt", "$29", "shirt4"),
        Product("Shirt Black", "$19", "shirt4")
    )

    val digitalGoods = listOf<Product>()

    fun getProducts(category: String) : List<Product> {
        return when(category) {
            "SHIRTS" -> shirts
            "HATS" -> hats
            "HOODIES" -> hoodies
            else -> digitalGoods
        }
    }

}