package com.example.android.projeto6;

import android.location.Location;

/**
 * Created by Pedro on 03/12/2017.
 */

public class Tour {
    private String tourDescription;
    private int tourImageResourceId = NO_IMAGE_PROVIDED;
    private Location tourLocation;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Tour(String description, int imageUrl, Location location) {
        tourDescription = description;
        tourImageResourceId = imageUrl;
        tourLocation = location;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public Location getTourLocation() {
        return tourLocation;
    }

    public int gettourImageResourceId() {
        return tourImageResourceId;
    }

    public boolean hasImage() {
        return tourImageResourceId != NO_IMAGE_PROVIDED;
    }
}
