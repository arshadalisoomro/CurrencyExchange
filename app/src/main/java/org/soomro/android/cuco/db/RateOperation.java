package org.soomro.android.cuco.db;

import org.soomro.android.cuco.model.Rate;

import java.util.List;

/**
 * Created by Arshay on 8/20/2017.
 */

public interface RateOperation {
    // Adding new Rate
    void addRate(Rate rate);

    // Getting single Rate
    Rate getRate(int id);

    // Finding single Rate
    double findRate(String code);

    // Getting All Rates
    List<Rate> getAllRates();

    // Getting Rates Count
    int getRatesCount();

    // Updating single Rate
    int updateRate(Rate rate);

    // Deleting single Rate
    void deleteRate(Rate rate);
}
