package com.myapp.domain.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myapp.domain.model.KaryawanModel;

import java.util.List;

public class ResponseGetKaryawan {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("data")
    @Expose
    private List<KaryawanModel> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<KaryawanModel> getData() {
        return data;
    }

    public void setData(List<KaryawanModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseGetKaryawan{" +
                "status=" + status +
                ", responseCode=" + responseCode +
                ", responseMessage='" + responseMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
