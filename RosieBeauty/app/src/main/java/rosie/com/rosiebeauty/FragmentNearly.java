package rosie.com.rosiebeauty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class FragmentNearly extends Fragment {
    Location currentLocation;
    LocationManager locationManager;

    public FragmentNearly() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nearly, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapNearly);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.setMyLocationEnabled(true);
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                List<String> pr = locationManager.getProviders(true);
                currentLocation = null;
                for (int i = 0; i < pr.size(); i++) {
                    currentLocation = locationManager.getLastKnownLocation(pr.get(i));
                    if (currentLocation != null) {
                        Log.d("bbb", currentLocation.getLatitude() + "");
                        break;
                    }
                }
                mMap.clear(); //clear old markers

                if (currentLocation != null) {
                    LatLng currentPos = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                    Log.d("aaa", "bbb");

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPos, 15));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                }

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.8151349, 106.697604))
                        .title("Rose Spa")
                        .snippet("Nơ Trang Long, Q. Bình Thạnh"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.8233244, 106.7050817))
                        .title("Rose Spa")
                        .snippet("Phạm Văn Đồng, Q. Bình Thạnh"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.8147498, 106.6893264))
                        .title("Rose Spa")
                        .snippet("Lê Quang Định, Q. Gò Vấp"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.803484, 106.6887525))
                        .title("Rose Spa")
                        .snippet("Phan Đăng Lưu, Q. Phú Nhuận"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.8026333, 106.7012415))
                        .title("Rose Spa")
                        .snippet("Phan Chu Trinh, Q. Bình Thạnh"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.768925, 106.6783052))
                        .title("Rose Spa")
                        .snippet("Nguyễn Thiện Thuật, Q.3"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.770785, 106.6843526))
                        .title("Rose Spa")
                        .snippet("Võ Văn Tần, Q.3"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.8005214, 106.6830849))
                        .title("Rose Spa")
                        .snippet("Phan Xích Long, Q.Phú Nhuận"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.7617781, 106.6913156))
                        .title("Rose Spa")
                        .snippet("Hồ Hảo Hớn, Q.1"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.7887217, 106.6756508))
                        .title("Rose Spa")
                        .snippet("Lê Văn Sĩ, Quận 3"));
            }
        });
        return rootView;
    }
}
