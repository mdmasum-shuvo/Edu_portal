
package com.masum.edu_portal.feature.home.data.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllPostsResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("postInfo")
    @Expose
    private PostInfo postInfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PostInfo getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(PostInfo postInfo) {
        this.postInfo = postInfo;
    }

}
