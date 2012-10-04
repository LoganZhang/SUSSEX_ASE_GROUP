package com.example.logan;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class MyLocation {

	public double[] getLocation(Activity activity) {
		LocationManager locMan = (LocationManager) activity
				.getSystemService(Context.LOCATION_SERVICE);

		Location location = locMan
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		double[] point = new double[2];

		if (location == null) {
			location = locMan
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		if (location != null) {
			point[2] = location.getLatitude();
			point[1] = location.getLongitude();
		}
		else
		{
			point[0] = 31.22;
			point[1] =121.48;
			
		}
		return point;

	}
}
