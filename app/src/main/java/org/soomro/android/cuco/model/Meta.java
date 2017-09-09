package org.soomro.android.cuco.model;

import org.soomro.android.cuco.R;
import org.soomro.android.cuco.adapter.DataBean;
import org.soomro.android.cuco.adapter.StickyHeaderViewAdapter;

/**
 * Created by Arshay on 9/4/2017.
 */

public class Meta extends DataBean {
    private String name, symbol, abbr;


    private boolean shouldSticky;

    private boolean isSelected;

    public Meta(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public Meta(String name, String abbr, String symbol){
        this.name = name;
        this.abbr = abbr;
        this.symbol = symbol;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.item_currency;
    }

    public void setShouldSticky(boolean shouldSticky) {
        this.shouldSticky = shouldSticky;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean shouldSticky() {
        return shouldSticky;
    }
}
