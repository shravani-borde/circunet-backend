package com.circunet.service;
import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class GeoServiceTest {private final GeoService geo=new GeoService(); @Test void samePointIsZero(){assertEquals(0,geo.distanceKm(21.1458,79.0882,21.1458,79.0882),0.001);} @Test void nagpurToPuneIsReasonable(){double d=geo.distanceKm(21.1458,79.0882,18.5204,73.8567);assertTrue(d>500&&d<700);}}
