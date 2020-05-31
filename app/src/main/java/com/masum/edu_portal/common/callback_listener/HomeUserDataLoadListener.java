/*
 * *
 *  * Created by Md Masum Talukder on 5/8/20 10:34 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/8/20 10:31 PM
 *
 */

package com.masum.edu_portal.common.callback_listener;


import com.masum.edu_portal.feature.home.data.about.AboutU;
import com.masum.edu_portal.feature.home.data.notifications.Notification;
import com.masum.edu_portal.feature.member.data.memberinfolist.Datum;

import java.util.List;

public interface HomeUserDataLoadListener {
    void getAboutUsDataList(NetworkRequestCompleteCallback<List<AboutU>> callback);
    void getMemberDataList(int page,NetworkRequestCompleteCallback<List<Datum>> callback);
    void getStatusList(int page,NetworkRequestCompleteCallback<List<com.masum.edu_portal.feature.home.data.posts.Datum>> callback);
    void getNotificationList(NetworkRequestCompleteCallback<List<Notification>> callback);

}
