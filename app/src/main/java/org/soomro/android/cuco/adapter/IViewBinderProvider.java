package org.soomro.android.cuco.adapter;

import android.support.v4.util.SparseArrayCompat;

public interface IViewBinderProvider {
    IViewBinder provideViewBinder(StickyHeaderViewAdapter adapter, SparseArrayCompat<? extends IViewBinder> viewBinderPool, int position);
}