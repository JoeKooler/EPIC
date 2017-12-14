package com.example.yoons.EPIC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoeKooler on 15/11/2017.
 */

public class DeviceBrandSelector
{
    static List<String> TVLIST;
    static List<String> AIRCONDITIONERLIST;
    static List<String> PROJECTORLIST;

    public static List<String> getTVList()
    {
        TVLIST = new ArrayList<>();
        TVLIST.add("Samsung");
        TVLIST.add("LG");
        TVLIST.add("Sony");
        TVLIST.add("Panasonic");
        TVLIST.add("Phillips");
        TVLIST.add("Toshiba");
        TVLIST.add("Sharp");
        TVLIST.add("Acer");
        TVLIST.add("BenQ");
        TVLIST.add("TCL");
        return TVLIST;
    }

    public static List<String> getAirConditionerList()
    {
        AIRCONDITIONERLIST = new ArrayList<>();
        AIRCONDITIONERLIST.add("Samsung");
        AIRCONDITIONERLIST.add("LG");
        AIRCONDITIONERLIST.add("Hitachi");
        AIRCONDITIONERLIST.add("Panasonic");
        AIRCONDITIONERLIST.add("Daikin");
        AIRCONDITIONERLIST.add("Mitsubishi");
        AIRCONDITIONERLIST.add("Haier");
        return AIRCONDITIONERLIST;
    }

    public static List<String> getProjectorList()
    {
        PROJECTORLIST = new ArrayList<>();
        PROJECTORLIST.add("Samsung");
        PROJECTORLIST.add("Sony");
        PROJECTORLIST.add("Sharp");
        PROJECTORLIST.add("Panasonic");
        PROJECTORLIST.add("Hitachi");
        PROJECTORLIST.add("Acer");
        PROJECTORLIST.add("Epson");
        PROJECTORLIST.add("Sanyo");
        PROJECTORLIST.add("BenQ");
        PROJECTORLIST.add("Optoma");
        PROJECTORLIST.add("Avermedia");
        return PROJECTORLIST;
    }
}
