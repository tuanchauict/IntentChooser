package com.tuanchauict.intentchooser;

import android.content.pm.PackageManager;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class Utils {
	public static boolean isPackageInstalled(PackageManager pm, String packageName){
		try{
			pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			return true;
		} catch(PackageManager.NameNotFoundException e){
			return false;
		}
	}
}
