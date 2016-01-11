package se.mah.ae2942.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Google maps fragment, add expense markers to map.
 */
public class GMapsFragment extends com.google.android.gms.maps.MapFragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    private Expense[] expenses;
    private Controller controller;
    private MainActivity mainActivity;

    public GMapsFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getMapAsync(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onMapReady(GoogleMap googleMap) {
        mainActivity = (MainActivity) getActivity();
        controller = mainActivity.getController();
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        setAllPosition();
    }

    /**
     * Set markers on map
     */
    public void setAllPosition(){
        double latitude,longitude;
        expenses = controller.getData();
        for(int i=0; i<expenses.length; i++ ){

            longitude = expenses[i].getLongitude();
            latitude = expenses[i].getLatitude();

            LatLng myPosition = new LatLng(latitude,longitude);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
            mMap.addMarker(new MarkerOptions().position(myPosition).title(expenses[i].getTitle()))
                    .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
        }
    }
}