package ysn.com.view.flowlayout2;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ysn.com.view.flowlayout2.base.BaseFlowLayout2Adapter;
import ysn.com.view.flowlayout2.bean.FlowLine2;

/**
 * @Author yangsanning
 * @ClassName FlowLayout2
 * @Description 流式布局
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public class FlowLayout2 extends ViewGroup {

    /**
     * 水平和竖直的间距
     */
    private float rowSpace;
    private float columnSpace;

    private List<FlowLine2> flowLine2List = new ArrayList<>();
    private BaseFlowLayout2Adapter adapter;

    public FlowLayout2(Context context) {
        this(context, null);
    }

    public FlowLayout2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout2);

        columnSpace = typedArray.getDimension(R.styleable.FlowLayout2_fl2_column_space, 0);
        rowSpace = typedArray.getDimension(R.styleable.FlowLayout2_fl2_row_space, 0);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取总宽度
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);

        // 子view进行归行处理
        initFlowLineList(widthMeasureSpec, heightMeasureSpec, viewWidth);

        // 测量
        setMeasuredDimension(viewWidth, measureViewHeight());
    }

    /**
     * 子view进行归行处理
     */
    private void initFlowLineList(int widthMeasureSpec, int heightMeasureSpec, int width) {
        // 清空重新添加
        flowLine2List.clear();

        // 行的最大宽度
        int maxFlowLineWidth = width - getPaddingLeft() - getPaddingRight();

        FlowLine2 flowLine2 = null;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            // 测量子View
            measureChild(view, widthMeasureSpec, heightMeasureSpec);

            // 为空或超出行宽度的时候需要重新创建新行
            boolean success = flowLine2 != null && flowLine2.addView(view);
            if (!success) {
                flowLine2 = new FlowLine2(maxFlowLineWidth, columnSpace);
                if (i == childCount - 1) {
                    flowLine2.setLast(true);
                }
                flowLine2.addView(view);
                flowLine2List.add(flowLine2);
            }

            if (i == childCount - 1) {
                flowLine2.setLast(true);
            }
        }
    }

    /**
     * 测量高度
     */
    private int measureViewHeight() {
        int viewHeight = getPaddingTop() + getPaddingBottom();
        for (FlowLine2 flowLine2 : flowLine2List) {
            // 加上所有子view高度
            viewHeight += flowLine2.getHeight();
        }
        // 加上所有行间距
        viewHeight += (flowLine2List.size() - 1) * rowSpace;
        return viewHeight;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        left = getPaddingLeft();
        top = getPaddingTop();
        for (int i = 0; i < flowLine2List.size(); i++) {
            FlowLine2 flowLine = flowLine2List.get(i);
            flowLine.layout(top, left);

            // 记录高度
            top += flowLine.getHeight();

            // 不是最后一行就添加行间距
            if (i != flowLine2List.size() - 1) {
                top += rowSpace;
            }
        }
    }

    public BaseFlowLayout2Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(final BaseFlowLayout2Adapter flowLayout2Adapter) {
        this.adapter = flowLayout2Adapter;
        this.adapter.bindFlowLayout2(this);
    }
}