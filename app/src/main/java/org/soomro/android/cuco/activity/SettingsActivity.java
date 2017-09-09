package org.soomro.android.cuco.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

import org.soomro.android.cuco.R;
import org.soomro.android.cuco.adapter.DataBean;
import org.soomro.android.cuco.adapter.StickyHeaderViewAdapter;
import org.soomro.android.cuco.header.ItemHeader;
import org.soomro.android.cuco.model.Meta;
import org.soomro.android.cuco.utils.Constant;
import org.soomro.android.cuco.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SettingsActivity extends Activity implements AdapterView.OnItemClickListener {

    private RecyclerView mChoiceRecyclerView;
    private List<DataBean> mCurrencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mChoiceRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SettingsActivity.this, LinearLayoutManager.VERTICAL, false);
        SharedPreferences choicePreferences = getSharedPreferences(Constant.CHOICE_PREF, Context.MODE_PRIVATE);
        int selectedItem = choicePreferences.getInt(Constant.CURRENT_CHOICE, -1);
        layoutManager.scrollToPosition(selectedItem);
        mChoiceRecyclerView.setLayoutManager(layoutManager);

        ImageButton backBtn = (ImageButton) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initData();

    }

    private void initData() {
        List<Meta> metas = Utils.getMetaList();
        Collections.sort(metas, new Comparator<Meta>() {
            @Override
            public int compare(Meta meta, Meta t1) {
                return meta.getName().compareToIgnoreCase(t1.getName());
            }
        });

        mCurrencyList = new ArrayList<>();
        String currentPrefix = metas.get(0).getName().substring(0, 1).toUpperCase();
        mCurrencyList.add(new ItemHeader(currentPrefix));
        for (Meta meta : metas) {
            if (currentPrefix.compareToIgnoreCase(meta.getName().substring(0, 1)) == 0)
                mCurrencyList.add(meta);
            else {
                currentPrefix = meta.getName().substring(0, 1).toUpperCase();
                mCurrencyList.add(new ItemHeader(currentPrefix));
                mCurrencyList.add(meta);
            }
        }

        StickyHeaderViewAdapter adapter = new StickyHeaderViewAdapter(SettingsActivity.this, mCurrencyList);
        adapter.setOnItemClickListener(this);
        mChoiceRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences choicePreferences = getSharedPreferences(Constant.CHOICE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = choicePreferences.edit();
        editor.putString(Constant.CURRENT_CHOICE_CODE, ((Meta)mCurrencyList.get(position)).getAbbr());
        editor.putInt(Constant.CURRENT_CHOICE, position);
        editor.apply();
        finish();
    }
}
