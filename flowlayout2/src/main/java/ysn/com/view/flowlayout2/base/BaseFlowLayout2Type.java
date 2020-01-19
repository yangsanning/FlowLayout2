package ysn.com.view.flowlayout2.base;

import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;

import java.util.List;

/**
 * @Author yangsanning
 * @ClassName BaseFlowLayout2Type
 * @Description 一句话概括作用
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public abstract class BaseFlowLayout2Type<T> {

    public static final int DEFAULT_VIEW_TYPE = -2020;
    private SparseIntArray layoutRess;

    protected BaseFlowLayout2Type() {
    }

    public int getItemType(List<T> data, int position) {
        T item = data.get(position);
        return item != null ? getItemType(item) : DEFAULT_VIEW_TYPE;
    }

    /**
     * 根据数据获取类型
     */
    protected abstract int getItemType(T t);

    /**
     * 根据类型获取布局id
     */
    final int getLayoutRes(int viewType) {
        return this.layoutRess.get(viewType, DEFAULT_VIEW_TYPE);
    }

    /**
     * 增加类型以及布局id
     *
     * @param viewType    类型
     * @param layoutResId 布局id
     */
    public BaseFlowLayout2Type<T> addItemType(int viewType, @LayoutRes int layoutResId) {
        if (this.layoutRess == null) {
            this.layoutRess = new SparseIntArray();
        }
        this.layoutRess.put(viewType, layoutResId);
        return this;
    }
}
