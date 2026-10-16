package com.circunet.service;
import org.springframework.stereotype.Service;
@Service public class GeoService { public double distanceKm(double lat1,double lon1,double lat2,double lon2){double r=6371.0088;double p1=Math.toRadians(lat1),p2=Math.toRadians(lat2),dp=Math.toRadians(lat2-lat1),dl=Math.toRadians(lon2-lon1);double a=Math.sin(dp/2)*Math.sin(dp/2)+Math.cos(p1)*Math.cos(p2)*Math.sin(dl/2)*Math.sin(dl/2);return 2*r*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));} }
