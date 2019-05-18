package com.example.nangao.myapplication;

import android.widget.Toast;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * @author chaochaowu
 */
class Position {


    static ArrayList<LatLng> getLatLngs(String objectid,ArrayList<LatLng> latLngs){
        final String objectid1 = objectid;
        final ArrayList<LatLng> latLngs1 = latLngs;

            MessageManager.getInstance().getMytable();

            //MessageManager.getInstance().getMytable().getTrack();
            // List<PositionPoint> track = new ArrayList<PositionPoint>();

            BmobQuery<MyTable> Query1 = new BmobQuery<MyTable>();
            Query1.findObjects(new FindListener<MyTable>() {
                @Override
                public void done(List<MyTable> list, BmobException e) {
                    int panduan = 1;ArrayList<LatLng> latLngs = new ArrayList<>();
                    //
                    for (int i = 0; i < list.size(); i++) {
                        String bobjectid = list.get(i).getObjectId();
                        if (bobjectid.equals(objectid1))
                        {
                            for(int j=0;j<5;j++)
                            {
                                String latitude = list.get(i).getTrack().get(j).getLatitude();
                                String longitude = list.get(i).getTrack().get(j).getLongitude();
                                double lon = Double.parseDouble(longitude);
                                double lat = Double.parseDouble(latitude);
                                latLngs1.add(new LatLng(lat,lon));//经度lon,纬度1at
                                break;
                            }

                        }
                    }
                }

            });
        return latLngs1;
        }
}
