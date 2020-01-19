package ysn.com.view.flowlayout2.base;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ysn.com.view.flowlayout2.FlowLayout2;

/**
 * @Author yangsanning
 * @ClassName BaseFlowLayout2Adapter
 * @Description FlowLayout2的adapter, 核心
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public abstract class BaseFlowLayout2Adapter<T, K extends BaseFlowLayout2ViewHolder> {

    private int defLayoutRes;
    private List<T> datas;
    private BaseFlowLayout2Type<T> flowLayout2Type;

    private int emptyLayoutRes = -1;
    private View emptyView;

    private FlowLayout2 flowLayout2;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    protected BaseFlowLayout2Adapter() {
    }

    protected BaseFlowLayout2Adapter(int defLayoutRes) {
        this.defLayoutRes = defLayoutRes;
    }

    /**
     * 创建空view
     */
    private View createEmptyView() {
        if (emptyLayoutRes == -1) {
            return null;
        }
        return emptyView = LayoutInflater.from(flowLayout2.getContext()).inflate(emptyLayoutRes, flowLayout2, false);
    }

    /**
     * 创建itemView
     */
    private View createBaseViewHolder(int position) {
        View itemView = LayoutInflater.from(flowLayout2.getContext())
            .inflate(getLayoutRes(position), flowLayout2, false);
        setItemViewClickListener(itemView, position);
        convert(new BaseFlowLayout2ViewHolder(itemView), position, datas.get(position));
        return itemView;
    }

    private int getLayoutRes(int position) {
        if (flowLayout2Type == null) {
            return defLayoutRes;
        }
        int itemType = getItemType(position);
        return itemType == BaseFlowLayout2Type.DEFAULT_VIEW_TYPE ? defLayoutRes : flowLayout2Type.getLayoutRes(itemType);
    }

    /**
     * 设置itemView的监听事件
     */
    private void setItemViewClickListener(final View itemView, final int position) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(BaseFlowLayout2Adapter.this, itemView, position);
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(BaseFlowLayout2Adapter.this, itemView, position);
                }
                return true;
            }
        });
    }

    /**
     * 绑定view或填充数据
     */
    public abstract void convert(BaseFlowLayout2ViewHolder holder, int position, T item);

    /**
     * 绑定FlowLayout2(将view的创建以及添加交由adapter处理)
     *
     * @see BaseFlowLayout2Adapter#notifyDataSetChanged()
     */
    public void bindFlowLayout2(FlowLayout2 parent) {
        this.flowLayout2 = parent;
        notifyDataSetChanged();
    }

    /**
     * 刷新数据(这里进行view的创建以及添加)
     */
    public void notifyDataSetChanged() {
        if (flowLayout2 == null) {
            return;
        }
        // 清空view
        flowLayout2.removeAllViews();

        int count = getCount();
        if (count == 0) {
            View emptyView = createEmptyView();
            if (emptyView != null) {
                flowLayout2.addView(emptyView);
            }
        } else {
            for (int position = 0; position < count; position++) {
                flowLayout2.addView(createBaseViewHolder(position));
            }
        }
    }

    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    public List<T> getDatas() {
        return datas;
    }

    /**
     * 需要调用了{@link FlowLayout2#setAdapter(BaseFlowLayout2Adapter)}方法
     * 以及调用了{@link #setEmptyLayoutRes(int)}方法后, emptyView方不为空
     */
    public View getEmptyView() {
        return emptyView;
    }

    /**
     * 设置数据为空时的占位view
     */
    public BaseFlowLayout2Adapter<T, K> setEmptyLayoutRes(int emptyLayoutRes) {
        this.emptyLayoutRes = emptyLayoutRes;
        notifyDataSetChanged();
        return this;
    }

    public void setNewData(@Nullable List<T> datas) {
        this.datas = datas == null ? new ArrayList<T>() : datas;
        notifyDataSetChanged();
    }

    /**
     * 新增数据
     */
    public void addData(@NonNull T data) {
        datas.add(data);
        notifyDataSetChanged();
    }

    /**
     * 新增数据
     */
    public void addData(@IntRange(from = 0) int position, @NonNull T data) {
        datas.add(position, data);
        notifyDataSetChanged();
    }

    /**
     * 新增数据
     */
    public void addData(@IntRange(from = 0) int position, @NonNull Collection<? extends T> datas) {
        this.datas.addAll(position, datas);
        notifyDataSetChanged();
    }

    /**
     * 移除数据
     */
    public void remove(@IntRange(from = 0) int position) {
        datas.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    /**
     * 更换数据
     */
    public void replaceData(@NonNull Collection<? extends T> datas) {
        // 不是同一个引用才清空列表
        if (datas != this.datas) {
            this.datas.clear();
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    /**
     * 设置多类型管理器{@link BaseFlowLayout2Type}
     *
     * @return
     */
    public BaseFlowLayout2Adapter<T, K> setFlowLayout2Type(BaseFlowLayout2Type flowLayout2Type) {
        this.flowLayout2Type = flowLayout2Type;
        return this;
    }

    /**
     * 获取多类型管理器{@link BaseFlowLayout2Type}
     */
    public BaseFlowLayout2Type getFlowLayout2Type() {
        return flowLayout2Type;
    }

    /**
     * 增加类型以及布局id
     *
     * @param viewType    类型
     * @param layoutResId 布局id
     */
    public void addItemType(int viewType, @LayoutRes int layoutResId) {
        flowLayout2Type.addItemType(viewType, layoutResId);
    }

    /**
     * 获取Item类型
     */
    public int getItemType(int position) {
        return flowLayout2Type.getItemType(datas, position);
    }

    /**
     * 设置itemView的点击事件监听
     */
    public BaseFlowLayout2Adapter<T, K> setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    /**
     * 设置itemView的长按事件监听
     */
    public BaseFlowLayout2Adapter<T, K> setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
        return this;
    }

    /**
     * itemView的点击事件
     */
    public interface OnItemClickListener {

        /**
         * itemView点击时回调
         */
        void onItemClick(BaseFlowLayout2Adapter adapter, View view, int position);
    }

    /**
     * itemView的长按事件
     */
    public interface OnItemLongClickListener {

        /**
         * itemView长按时回调
         */
        void onItemLongClick(BaseFlowLayout2Adapter adapter, View view, int position);
    }
}
