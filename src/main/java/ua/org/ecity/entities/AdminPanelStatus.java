package ua.org.ecity.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AdminPanelStatus {

    CITY_HAS_BEEN_ADDED(100, "City has been added"),
    CITY_HAS_BEEN_CHANGED(101, "City has been changed"),
    CITY_HAS_BEEN_DELETED(102, "City has been deleted"),
    CITY_IS_IN_DATABASE(103, " City is already in database"),
    CITY_NOT_FOUND(104, "City not found"),
    REGION_HAS_BEEN_ADDED(110, "Region has been added"),
    REGION_HAS_BEEN_CHANGED(111, "Region has been changed"),
    REGION_HAS_BEEN_DELETED(112, "Region has been deleted"),
    REGION_IS_IN_DATABASE(113, " Region is already in database"),
    REGION_NOT_FOUND(114, "Region not found");

    AdminPanelStatus(int code, String message) {
        this.code = code;
        this.message = message;

    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
