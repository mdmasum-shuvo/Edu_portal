
package com.masum.edu_portal.feature.home.data.about;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AboutUsDataResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("about_us")
    @Expose
    private List<AboutU> aboutUs = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AboutU> getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(List<AboutU> aboutUs) {
        this.aboutUs = aboutUs;
    }

}
