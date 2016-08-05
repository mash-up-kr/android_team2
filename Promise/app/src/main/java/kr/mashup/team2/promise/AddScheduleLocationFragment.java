package kr.mashup.team2.promise;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.mashup.team2.promise.Map.MapApiConst;
import kr.mashup.team2.promise.Map.NetworkThread;
import kr.mashup.team2.promise.Map.OnFinishSearchListener;
import kr.mashup.team2.promise.Map.SearchItem;
import kr.mashup.team2.promise.Map.SearchItemViewAdapter;
import kr.mashup.team2.promise.Map.Searcher;

public class AddScheduleLocationFragment extends Fragment {

    private final String API_KEY = MapApiConst.DAUM_MAPS_ANDROID_APP_API_KEY;

    @BindView(R.id.btn_map_search) Button btnSearch;
    @BindView(R.id.et_map_search) EditText etSearch;

    @BindView(R.id.map_view) RelativeLayout mapViewContainer;
    @BindView(R.id.recycler_view_map) RecyclerView recyclerView;
    MapView mapView;
    View view;

    private HashMap<Integer, SearchItem> mTagItemMap = new HashMap<Integer, SearchItem>();

    List<SearchItem> searchItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_schedule_location, container, false);
        ButterKnife.bind(this, view);

        // Using TedPermission library
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // MapView 객체생성 및 API Key 설정
                mapView = new MapView(getContext());
                mapView.setDaumMapApiKey(API_KEY);

                mapViewContainer.addView(mapView);

                // 중심점 변경
                mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), -1, true);

                // MapViewEventListener 등록
                mapView.setMapViewEventListener(mapListener);

                // 마커 생성 및 설정
                MapPOIItem marker = new MapPOIItem();
                marker.setItemName("Default Marker");
                marker.setTag(0);
                marker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633));
                marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
                marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                mapView.addPOIItem(marker);

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getContext(), "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }

        };

        new TedPermission(getContext())
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("지도 서비스를 사용하기 위해서는 위치 접근 권한이 필요합니다")
                .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있습니다")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        setCustomActionbar();
        return view;
    }


    @OnClick(R.id.btn_map_search)
    public void onSearchClick(View v) {
        String query = etSearch.getText().toString();
        if (query == null || query.length() == 0) {
            Toast.makeText(getContext(), "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        MapPoint.GeoCoordinate geoCoordinate = mapView.getMapCenterPoint().getMapPointGeoCoord();
        double latitude = geoCoordinate.latitude; // 위도
        double longitude = geoCoordinate.longitude; // 경도
        int radius = 10000; // 중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 사용. meter 단위 (0 ~ 10000)
        int page = 1; // 페이지 번호 (1 ~ 3). 한페이지에 15개
        String apikey = MapApiConst.DAUM_MAPS_ANDROID_APP_API_KEY;

        Searcher searcher = new Searcher(); // net.daum.android.map.openapi.search.Searcher
        searcher.searchKeyword(getContext(), query, latitude, longitude, radius, page, apikey, new OnFinishSearchListener() {
            @Override
            public void onSuccess(List<SearchItem> itemList) {
                searchItemList = itemList;
                mapView.removeAllPOIItems(); // 기존 검색 결과 삭제
                showResultInMap(itemList); // 검색 결과 보여줌

                NetworkThread thread = new NetworkThread(handler);
                thread.setDaemon(true);
                thread.start();
            }

            @Override
            public void onFail() {
                Toast.makeText(getContext(), "API_KEY의 제한 트래픽이 초과되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            showResultInList(searchItemList);
        }
    };

    private void showResultInList(List<SearchItem> itemList) {
        SearchItemViewAdapter itemViewAdapter = new SearchItemViewAdapter(getContext(), itemList);
        recyclerView.setAdapter(itemViewAdapter);
    }

    private void showResultInMap(List<SearchItem> itemList) {
        MapPointBounds mapPointBounds = new MapPointBounds();

        for (int i = 0; i < itemList.size(); i++) {
            SearchItem item = itemList.get(i);

            MapPOIItem poiItem = new MapPOIItem();
            poiItem.setItemName(item.title);
            poiItem.setTag(i);
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(item.latitude, item.longitude);
            poiItem.setMapPoint(mapPoint);
            mapPointBounds.add(mapPoint);
            poiItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            poiItem.setCustomImageResourceId(R.drawable.map_pin_blue);
            poiItem.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);
            poiItem.setCustomSelectedImageResourceId(R.drawable.map_pin_red);
            poiItem.setCustomImageAutoscale(false);
            poiItem.setCustomImageAnchor(0.5f, 1.0f);

            mapView.addPOIItem(poiItem);
            mTagItemMap.put(poiItem.getTag(), item);
        }

        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds));

        MapPOIItem[] poiItems = mapView.getPOIItems();
        if (poiItems.length > 0) {
            mapView.selectPOIItem(poiItems[0], false);
        }
    }

    MapView.MapViewEventListener mapListener = new MapView.MapViewEventListener() {
        @Override
        public void onMapViewInitialized(MapView mapView) {

        }

        @Override
        public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewZoomLevelChanged(MapView mapView, int i) {

        }

        @Override
        public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
//            Toast.makeText(getContext(), "터치", Toast.LENGTH_SHORT).show();
//            // 마커 리셋
//            mapView.removeAllPOIItems();
//
//            // 마커 클릭위치로 중심점 이동
//            mapView.setMapCenterPoint(mapPoint, true);
//
//            // 마커 생성 및 설정
//            MapPOIItem marker = new MapPOIItem();
//            marker.setItemName("Default Marker");
//            marker.setTag(0);
//            marker.setMapPoint(MapPoint.mapPointWithGeoCoord(
//                    mapPoint.getMapPointGeoCoord().latitude,
//                    mapPoint.getMapPointGeoCoord().longitude));
//            marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
//            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//            mapView.addPOIItem(marker);
        }

        @Override
        public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

        }
    };

    private void setCustomActionbar() {

        ActionBar actionBar = ((AddScheduleActivity)getActivity()).getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View customView = LayoutInflater.from(getContext()).inflate(R.layout.actionbar_add_schedule_location, null);
        actionBar.setCustomView(customView);

        Toolbar parent = (Toolbar) customView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(customView, params);


    }
}
