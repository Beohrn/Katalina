package com.katalina.katalina.intents;


import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;

public class PackageInfo {

    public String appname = "";
    public String pname = "";
    public String versionName = "";
    public int versionCode = 0;
    public Drawable icon;

    public void prettyPrint() {
        Log.v("LOLOLOLO",appname + "\t" + pname + "\t" + versionName + "\t" + versionCode);
    }

}
