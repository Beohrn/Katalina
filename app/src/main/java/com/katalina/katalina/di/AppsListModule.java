package com.katalina.katalina.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.katalina.katalina.intents.PackageInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppsListModule {

    @Provides
    @NonNull
    @Singleton
    public List<PackageInfo> proviceAppsList(Context context) {

        boolean getSysPackages = false;
        ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
        List<android.content.pm.PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            android.content.pm.PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue;
            }
            PackageInfo newInfo = new PackageInfo();
            newInfo.appname = p.applicationInfo.loadLabel(context.getPackageManager()).toString();
            newInfo.pname = p.packageName;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(context.getPackageManager());
            res.add(newInfo);
        }
        return res;
    }
}
