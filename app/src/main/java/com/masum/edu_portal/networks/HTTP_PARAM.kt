package com.masum.edu_portal.networks

object HTTP_PARAM {

    //commitee
    const val COMMITEE_LIST = "CommitteeList"



    //member status
    const val STATUS_LIST = "PostList"
    const val MEMBER_LIST = "MemberList"
    const val STATUS_POST = "PostSave"
    const val DESIGNATION_LIST = "DesignationList"
    const val MEMBER_UPDATE = "MemberUpdate"
    const val MEMBER_INFO = "MemberInfo"

    //notification
    const val NOTIFICATION = "Notifications"
    const val NOTIFICATION_POST = "NotificationsDetailsSave"

    //gallery
    const val GALLERY_INFO = "GalleryInfo"
    const val GALLERY_DELETE = "GalleryDelete"

    //api url of hall
    const val HALL_INFO = "HallInfo"
    const val HALL_BOOKING_CHECK = "HallBookingCheck"
    const val HALL_BOOKING_POST = "HallBookingSave"
    const val HALL_BOOKING_INFO = "HallBookingInfo"

    //auth
    const val LOGIN="MemberValidation"

    //about
    const val ABOUT_US = "AboutUs"

    //api url of ticket/coupon
    const val TICKET_DETAIL = "TicketCouponDetailsInfo"
    const val TICKET_POST = "TicketCouponSave"
    const val TICKET_INFO = "TicketCouponInfo"
    const val FOOD_ORDER_POST = "OrderSave"
    const val TABLE_INFO = "TableInfo"
    const val ORDER_UPDATE = "OrderUpdate"
    const val FOOD_CATEGORY_LIST = "CategoryInfo"
    const val FOOD_ITEM_LIST = "ProductInfo"
    const val MEMBER_ORDER_LIST = "MemberOrderList"


    //field
    const val MEMBER_ID = "member_id"
    const val PAGE_NUMBER = "page"
    const val CATEGORY_ID = "category_id"

    //auth
    const val MEMBERSHIP_ID = "membership_id"
    const val MOBILE_NO = "mobile"

    // hall
    const val HALL_CAT_ID = "hall_category_id"
    const val HALL_BOOK_DATE = "hall_book_date"
    const val HALL_SHIFT_TYPE = "hall_shift_type"
    const val RENT_CAT = "rent_category"

    const val HALL_BOOKING_PRICE = "booking_price"




    /*         builder.addFormDataPart("member_id", SharedPref.read("mid", ""));
        //builder.addFormDataPart("member_id", "10");
        builder.addFormDataPart("hall_category_id", "" + hallInfo.getHallCategoryId());
        builder.addFormDataPart("hall_book_date", date);
        builder.addFormDataPart("rent_category", bookReason);
        builder.addFormDataPart("hall_shift_type", shift);
        //builder.addFormDataPart("booking_price", hallInfo.getHallRent());
        builder.addFormDataPart("booking_price", "" + hallInfo.getHallPrice());*/
}