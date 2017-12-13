package com.example.yoons.EPIC;

/**
 * Created by JoeKooler on 11/11/2017.
 */

public class Device
{
    String Type;
    String Brand;
    String Version;
    String UID;
    String Power;
    int Temp;

    public Device()
    {}

    public Device(String Type, String Brand, String Version)
    {
        this.Type = Type;
        this.Brand = Brand;
        this.Version = Version;
    }

    public Device(String Type, String Brand, String Version,String UID,String Power,int Temp)
    {
        this.Type = Type;
        this.Brand = Brand;
        this.Version = Version;
        this.UID = UID;
        this.Power = Power;
        this.Temp = Temp;
    }

    public Device(String Type, String Brand, String Version,String UID)
    {
        this.Type = Type;
        this.Brand = Brand;
        this.Version = Version;
        this.UID = UID;
    }

}
