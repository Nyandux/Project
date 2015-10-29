package se.mah.ae2942.project;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
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


public class GMapsFragment extends com.google.android.gms.maps.MapFragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Expense[] expenses;
    private Controller controller;


    public GMapsFragment() {}

    /**
     * Sets local controller.
     * @param controller controller
     */
    public void setController(Controller controller){
        this.controller = controller;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getMapAsync(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        setAllPosition();
        setMyposition();


//        LatLng myPosition = new LatLng(latitude, longtitude);
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
//        mMap.addMarker(new MarkerOptions().position(myPosition).title("Marker in Sydney"))
//                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.money_icon));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
//
//        LatLng anotherPosition = new LatLng(20, 15);
//        mMap.addMarker(new MarkerOptions().position(anotherPosition).title("HERB its here"))
//                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));

    }


    public void setAllPosition(){
        double latitude,longtitude;
        expenses = controller.getData();
        for(int i=0; i<expenses.length; i++ ){

           latitude = expenses[i].getLatitude();
           longtitude = expenses[i].getLongitude();

            locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

            LatLng myPosition = new LatLng(latitude,longtitude);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
            mMap.addMarker(new MarkerOptions().position(myPosition).title(expenses[i].getTitle()))
                    .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
        }
    }

    public void setMyposition(){
        double latitude,longtitude;

        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);

        latitude = location.getLatitude();
        longtitude = location.getLongitude();

        LatLng myPosition = new LatLng(latitude,longtitude);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
        mMap.addMarker(new MarkerOptions().position(myPosition).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
    }

}