package org.soomro.android.cuco.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Arshay on 8/23/2017.
 */

public class Currency implements Serializable {
    @Expose
    private long id;
    @Expose
    private String abbr;
    @Expose
    private double rate;

    public long getId() {
        return id;
    }

    public String getAbbr() {
        return abbr;
    }

    public double getRate() {
        return rate;
    }
}
