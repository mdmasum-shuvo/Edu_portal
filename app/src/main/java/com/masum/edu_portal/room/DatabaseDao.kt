package com.masum.edu_portal.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.masum.edu_portal.room.datamodel.FoodItemCart

@Dao
interface DatabaseDao {
    @Insert
    fun insertCart(request: FoodItemCart?): Long

    @Query("UPDATE food_item SET quantity=:quantity1 where code=:code")
    fun updateFoodItem(code: String?, quantity1: Int): Int

    @Query("Select * from food_item where code=:code")
    fun getItemByCode(code: String?): Int

    @Query("delete from food_item where id=:id")
    fun deleteItem(id: Int): Int

    @get:Query("Select * from food_item")
    val allItem: List<FoodItemCart?>?

    @get:Query("SELECT COUNT(id) FROM food_item")
    val rowCount: Long?

    @Query("delete from food_item")
    fun deleteAllItem()
}