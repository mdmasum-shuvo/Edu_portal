package com.masum.edu_portal.room.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.masum.edu_portal.room.RoomConstant

@Entity(tableName = RoomConstant.FOOD_ITEM)
class FoodItemCart {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "image")
    var image: String? = null

    @ColumnInfo(name = "price")
    var price = 0

    @ColumnInfo(name = "quantity")
    var quantity = 0

    @ColumnInfo(name = "code")
    var code: String? = null

    @ColumnInfo(name = "product_id")
    var productId: String? = null

    @ColumnInfo(name = "cat_id")
    var catID: String? = null

}