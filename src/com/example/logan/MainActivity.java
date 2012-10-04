package com.example.logan;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MainActivity extends MapActivity {

	private MapView mapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mapView = (MapView) findViewById(R.id.map);
		mapView.setBuiltInZoomControls(true);
		
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.red_arrow_down);
        CustomItemizedOverlay itemizedOverlay = 
             new CustomItemizedOverlay(drawable, this);
        
        MyLocation location = new MyLocation();
        double [] temp = location.getLocation(this);
        double latitude = temp[0];
        double longitude = temp[1];
        
        
        GeoPoint point = new GeoPoint((int)(latitude*1E6), (int)(longitude*1E6));        
        String address ="kk";//; GetAddressFromGeo.getAddressByLatLng(latitude+","+longitude);
        
        OverlayItem overlayitem = 
             new OverlayItem(point,getBaseContext().getResources().
            		 getText(R.string.main_location).toString(),address);
        
        mapView.setSatellite(false);
                
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
        MapController mapController = mapView.getController();
        mapController.animateTo(point);
        mapController.setZoom(18);

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}