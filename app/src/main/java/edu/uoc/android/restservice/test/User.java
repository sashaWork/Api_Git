package edu.uoc.android.restservice.test;


import com.google.gson.annotations.SerializedName;

public class User {


//    @SerializedName("id")
//    private Integer id;
//
//    public Integer getId() {
//        return id;
//    }

    private static String id;
    private static String type;
    private String statusMessage;

    public static String getResource_id() {
        return id;
    }


    public static String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }


//    @Override
//    public String toString() {
////        return "LoginResponse [statusCode = "+  "+id+" + type = "+type+", statusMessage = "+statusMessage+"]";
//    }
}
