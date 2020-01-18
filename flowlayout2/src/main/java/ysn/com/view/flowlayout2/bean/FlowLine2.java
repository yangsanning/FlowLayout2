package ysn.com.view.flowlayout2.bean;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangsanning
 * @ClassName FlowLine2
 * @Description 每一行view集合
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public class FlowLine2 {

    /**
     * maxWidth: 最大宽度
     * columnSpace: 列间距(view之间的间距)
     * height: 当前行宽高
     * height: 当前行宽高
     */
    private int maxWidth;
    private float columnSpace;
    private int height;
    private int width;

    /**
     * 是否最后一行(非第一行时, 把剩余空间进行平均使用)
     */
    private boolean isLast;
    private List<View> viewList = new ArrayList<>();

    public FlowLine2(int maxWidth, float columnSpace) {
        this.maxWidth = maxWidth;
        this.columnSpace = columnSpace;
    }

    /**
     * @param view 需要添加的view
     * @return true添加成功, false添加失败
     */
    public boolean addView(View view) {
        if (isFull(view)) {
            return Boolean.FALSE;
        }
        int viewWidth = view.getMeasuredWidth();
        int viewHeight = view.getMeasuredHeight();

        if (viewList.size() == 0) {
            if (viewWidth > maxWidth) {
                width = maxWidth;
                height = viewHeight;
            } else {
                width = viewWidth;
                height = viewHeight;
            }
        } else {
            width += viewWidth + columnSpace;
            height = viewHeight > height ? viewHeight : height;
        }
        viewList.add(view);
        return Boolean.TRUE;
    }

    /**
     * 是否溢出（用于判断是否还能添加view）
     */
    private boolean isFull(View view) {
        if (viewList.size() == 0) {
            return false;
        }
        return view.getMeasuredWidth() > (maxWidth - width - columnSpace);
    }

    /**
     * 把子view位置安排的明明白白
     */
    public void layout(int top, int left) {
        // 平分剩下的空间
        int avg = (maxWidth - width) / viewList.size();

        for (View view : viewList) {
            // 获取宽高
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();

            if (isLast) {
                // 重新测量
                view.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY));
            } else {
                // 重新测量
                view.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth + avg, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY));
            }

            // 重新获取宽度值
            measuredWidth = view.getMeasuredWidth();

            // 安排位置
            view.layout(left, top, (measuredWidth + left), (measuredHeight + top));

            // 更新数据
            left += measuredWidth + columnSpace;
        }
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public float getColumnSpace() {
        return columnSpace;
    }

    public void setColumnSpace(float columnSpace) {
        this.columnSpace = columnSpace;
    }

    public List<View> getViewList() {
        return viewList;
    }

    public void setViewList(List<View> viewList) {
        this.viewList = viewList;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
