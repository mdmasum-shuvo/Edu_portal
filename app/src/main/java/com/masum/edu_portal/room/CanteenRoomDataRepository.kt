package com.masum.edu_portal.room

import android.content.Context
import com.masum.edu_portal.room.datamodel.FoodItemCart

class CanteenRoomDataRepository private constructor(private val mDatabase: MyDatabase?) {


   companion object {
        private var sInstance: CanteenRoomDataRepository? = null
       open fun getInstance(context: Context?): CanteenRoomDataRepository? {
            if (sInstance == null) {
                synchronized(CanteenRoomDataRepository::class.java) {
                    if (sInstance == null) {
                        sInstance =
                            CanteenRoomDataRepository(
                                MyDatabase.getInstance(
                                    context
                                )
                            )
                    }
                }
            }
            return sInstance
        }
    }

    fun insertToCart(request: FoodItemCart?): Long {
        return mDatabase!!.myDatabaseDao().insertCart(request)
    }

    fun updateItemData(itemCart: FoodItemCart): Long {
        return mDatabase!!.myDatabaseDao().updateFoodItem(itemCart.code, itemCart.quantity)
            .toLong()
    }

    fun getItemByCode(request: String?): Int {
        return mDatabase!!.myDatabaseDao().getItemByCode(request)
    }

    fun deleteItem(deleteItem: Int): Int {
        return mDatabase!!.myDatabaseDao().deleteItem(deleteItem)
    }

    fun deleteAllItem() {
        mDatabase!!.myDatabaseDao().deleteAllItem()
    }

    val allItem: List<FoodItemCart?>?
        get() = mDatabase!!.myDatabaseDao().allItem

    val rowCount: Long?
        get() = mDatabase!!.myDatabaseDao().rowCount

}