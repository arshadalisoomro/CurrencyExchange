package org.soomro.android.cuco.header;

import org.soomro.android.cuco.R;
import org.soomro.android.cuco.adapter.DataBean;
import org.soomro.android.cuco.adapter.StickyHeaderViewAdapter;

/**
 * Created by tlh on 2017/1/22 :)
 */

public class ItemHeader extends DataBean {
    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public ItemHeader(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.header;
    }

    @Override
    public boolean shouldSticky() {
        return true;
    }
}
