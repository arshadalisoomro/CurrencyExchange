package org.soomro.android.cuco.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;


import org.soomro.android.cuco.R;
import org.soomro.android.cuco.header.ItemHeader;
import org.soomro.android.cuco.model.Meta;
import org.soomro.android.cuco.utils.Constant;
import org.soomro.android.cuco.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class StickyHeaderViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int mSelectedItem;
    private static int SIZE_VIEW_BINDER_POOL = 10;
    private SparseArrayCompat<ViewBinder> viewBinderPool;
    protected List<DataBean> displayList;
    private DataSetChangeListener onDataSetChangeListener;
    private AdapterView.OnItemClickListener onItemClickListener;

    public StickyHeaderViewAdapter(Context context, List<? extends DataBean> displayList) {
        viewBinderPool = new SparseArrayCompat<>(SIZE_VIEW_BINDER_POOL);
        this.displayList = new ArrayList<>();
        this.displayList.addAll(displayList);

        this.RegisterItemType(new MetaViewBinder(context, this));
        this.RegisterItemType(new ItemHeaderViewBinder(context));

        SharedPreferences choicePreferences = context.getSharedPreferences(Constant.CHOICE_PREF, Context.MODE_PRIVATE);
        mSelectedItem = choicePreferences.getInt(Constant.CURRENT_CHOICE, -1);

    }

    private List<DataBean> getDisplayList() {
        return displayList;
    }

    public int getDisplayListSize() {
        return displayList == null ? 0 : displayList.size();
    }

    public DataBean get(int i) {
        if (i < getDisplayListSize())
            return displayList.get(i);
        else return null;
    }

    public int getPosition(DataBean dataBean) {
        return displayList.indexOf(dataBean);
    }

    @Override
    public int getItemViewType(int position) {
        return displayList.get(position).getItemLayoutId(this);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewBinder viewBinder = getViewBinder(viewType);
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(viewBinder.getItemLayoutId(this), parent, false);
        return viewBinder.provideViewHolder(itemView);
    }

    public ViewBinder getViewBinder(int viewType) {
        return viewBinderPool.get(viewType);
    }

    public abstract class ViewBinder<T extends IViewBinderProvider, VH extends RecyclerView.ViewHolder> implements IViewBinder<T, VH> {
        public abstract VH provideViewHolder(View itemView);

        public abstract void bindView(StickyHeaderViewAdapter adapter, VH holder, int position, T entity);

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View rootView) {
                super(rootView);
            }

            protected <T extends View> T findViewById(@IdRes int id) {
                return (T) itemView.findViewById(id);
            }
        }

    }




    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //normal view
        DataBean dataBean = displayList.get(position);
        dataBean.provideViewBinder(this, viewBinderPool, position).bindView(this, holder, position, dataBean);


        try {
            if (!(displayList.get(position) instanceof ItemHeader)){
                Meta item = (Meta)displayList.get(position);
                ((ChoiceHolder)holder).setDateToView(item, position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return displayList == null ? 0 : displayList.size();
    }

    public ViewBinder findViewBinderFromPool(@LayoutRes int layoutId) {
        return viewBinderPool.get(layoutId);
    }

    public static void setViewBinderPoolInitSize(int size) {
        SIZE_VIEW_BINDER_POOL = size;
    }

    public void clearViewBinderCache() {
        viewBinderPool.clear();
    }



    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void onItemHolderClick(StickyHeaderViewAdapter.ChoiceHolder holder) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(null, holder.itemView, holder.getAdapterPosition(), holder.getItemId());
    }


    /**
     *
     * Class
     */


    public class MetaViewBinder extends ViewBinder<Meta, StickyHeaderViewAdapter.ChoiceHolder> {

        private Context context;

        private StickyHeaderViewAdapter mAdapter;
        public MetaViewBinder(Context context, StickyHeaderViewAdapter mAdapter){
            this.mAdapter = mAdapter;

            this.context = context;
        }

        @Override
        public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
            return R.layout.item_currency;
        }

        @Override
        public ChoiceHolder provideViewHolder(View itemView) {
            return new ChoiceHolder(itemView, mAdapter);
        }

        @Override
        public void bindView(StickyHeaderViewAdapter adapter, ChoiceHolder holder, int position, Meta entity) {
            holder.title.setText(String.valueOf(entity.getName()));
            holder.title.setTypeface(Utils.getFontFace(context));

            holder.code.setText(String.valueOf(entity.getAbbr()));
            holder.code.setTypeface(Utils.getFontFace(context));

        }
    }

    public class ItemHeaderViewBinder extends ViewBinder<ItemHeader, ItemHeaderViewBinder.ViewHolder> {

        private Context context;

        public ItemHeaderViewBinder(Context context){
            this.context = context;
        }

        @Override
        public ItemHeaderViewBinder.ViewHolder provideViewHolder(View itemView) {
            return new ViewHolder(itemView);
        }

        @Override
        public void bindView(StickyHeaderViewAdapter adapter, ViewHolder holder, int position, ItemHeader entity) {
            holder.tvPrefix.setText(entity.getPrefix());
        }

        @Override
        public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
            return R.layout.header;
        }


        class ViewHolder extends ViewBinder.ViewHolder {
            TextView tvPrefix;

            public ViewHolder(View rootView) {
                super(rootView);
                this.tvPrefix = (TextView) rootView.findViewById(R.id.tv_prefix);
                this.tvPrefix.setTypeface(Utils.getFontFace(context));
            }

        }
    }

    class ChoiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private StickyHeaderViewAdapter mAdapter;
        public TextView title, code;
        private CheckBox checkBox;

        public ChoiceHolder(View itemView, StickyHeaderViewAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            title = (TextView) itemView.findViewById(R.id.currency_title);
            checkBox = (CheckBox) itemView.findViewById(R.id.currency_choice);
            code = (TextView) itemView.findViewById(R.id.currency_code);

            itemView.setOnClickListener(this);
            checkBox.setOnClickListener(this);
        }


        public void setDateToView(Meta item, int position) throws Exception {
            //mSelectedItem = 10;
            checkBox.setChecked(position == mSelectedItem);
            title.setText(item.getName());
        }

        @Override
        public void onClick(View v) {
            mSelectedItem = getAdapterPosition();
            notifyItemRangeChanged(0, displayList.size());
            mAdapter.onItemHolderClick(ChoiceHolder.this);
        }
    }

    public void append(List<? extends DataBean> list) {
        if (list == null)
            return;
        displayList.addAll(list);
        notifyDataSetChanged();
    }

    public void append(DataBean dataBean) {
        displayList.add(dataBean);
        notifyItemInserted(displayList.size() - 1);
    }

    public void refresh(List<? extends DataBean> list) {
        if (list == null)
            return;
        onDataSetChangeListener.onClearAll();
        displayList.clear();
        displayList.addAll(list);
        notifyDataSetChanged();
    }

    public void delete(int pos) {
        onDataSetChangeListener.remove(pos);
        displayList.remove(pos);
        notifyItemRemoved(pos);
    }

    public void clear(RecyclerView recyclerView) {
        onDataSetChangeListener.onClearAll();
        displayList.clear();
        notifyDataSetChanged();
        recyclerView.scrollToPosition(0);
    }

    public StickyHeaderViewAdapter RegisterItemType(ViewBinder viewBinder) {
        viewBinderPool.put(viewBinder.getItemLayoutId(this), viewBinder);
        return this;
    }

    // Don't Call this Method
    public void setDataSetChangeListener(DataSetChangeListener listener) {
        this.onDataSetChangeListener = listener;
    }

    public interface DataSetChangeListener {
        void onClearAll();

        void remove(int pos);
    }
}
