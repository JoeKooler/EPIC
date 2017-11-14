package com.example.yoons.EPIC;

/**
 * Created by JoeKooler on 11/11/2017.
 */

public class Device
{
    private String deviceType;
    private String deviceBrand;
    private String deviceVersion;
    static final String PREDEVICETYPE[] = {"TV","Air-Conditioner","Projector"};

    public Device()
    {}

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public Device(String deviceType, String deviceBrand, String deviceVersion)
    {
        this.deviceType = deviceType;

        this.deviceBrand = deviceBrand;
        this.deviceVersion = deviceVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public static final String[] getPreDeviceType()
    {
        return PREDEVICETYPE;
    }
}
