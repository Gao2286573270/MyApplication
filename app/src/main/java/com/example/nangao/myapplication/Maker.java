package com.example.nangao.myapplication;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;


/**
 * @author chaochaowu
 */
class Maker {

    static ArrayList<LatLng> getLatLngs(){
        ArrayList<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(39.962667,116.355479));
        latLngs.add(new LatLng(39.962423,116.355479));
        latLngs.add(new LatLng(39.962257,116.355479));
        latLngs.add(new LatLng(39.961667,116.355479));
        latLngs.add(new LatLng(39.961467,116.355479));
        latLngs.add(new LatLng(39.961080,116.355230));
        return latLngs;
    }

}
