package com.rikkei.kiendd.mvvmbaseproject.base;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    protected Context mContext;

    protected List<T> mListItems;

    protected OnItemClickListener mListener;

    private OnLoadMoreListener mLoadMoreListener;

    protected RecyclerView recyclerView;

    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    protected boolean loading = false;
    private int pageNumber = 1;

    protected static final int ITEM_NORMAL = 1;
    protected static final int ITEM_LOADING = 2;
    public static final int PAGE_SIZE = 1; // TODO: Sẽ update lại theo server

    public BaseAdapter(Context context, List<T> mListItems) {
        this.mContext = context;
        this.mListItems = mListItems;
    }

    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onActualCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        T data = mListItems.get(position);
        holder.bind(data);
        holder.setListener(mListener);
        onActualBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public void setData(List<T> data) {
        this.mListItems = new ArrayList<>(data);
    }

    public void addItems(List<T> items) {
        this.mListItems.addAll(new ArrayList<>(items));
        this.notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mListItems.get(position);
    }

    public List<T> getmListItems() {
        return mListItems;
    }

    public abstract BaseViewHolder<T> onActualCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public abstract void onActualBindViewHolder(@NonNull BaseViewHolder<T> holder, int position);

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onClickItem(int position, T item);
    }

    // ----------- Support LoadMore ---------- //

    private boolean isLoadMoreAble = true;

    public boolean isLoadMoreAble() {
        return isLoadMoreAble;
    }

    public void setLoadMoreAble(boolean loadMoreAble) {
        isLoadMoreAble = loadMoreAble;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setLoadMoreListener(RecyclerView recyclerView, OnLoadMoreListener loadMoreListener) {
        this.recyclerView = recyclerView;
        this.mLoadMoreListener = loadMoreListener;

        initLoadMore();
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyItemRemoved(mListItems.size());
    }

    private void initLoadMore() {
        if (recyclerView == null || mLoadMoreListener == null) {
            return;
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) {
                    return;
                }

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (isLoadMoreAble && !loading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD) && totalItemCount > 0) {
                    pageNumber++;
                    mLoadMoreListener.onLoadMore(pageNumber);
                    loading = true;
                    notifyItemInserted(mListItems.size());
                }
            }
        });
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int pageNum);
    }
}
