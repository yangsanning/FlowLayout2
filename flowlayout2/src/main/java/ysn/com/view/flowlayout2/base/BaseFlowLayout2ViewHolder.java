package ysn.com.view.flowlayout2.base;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Author yangsanning
 * @ClassName BaseFlowLayout2ViewHolder
 * @Description ViewHolder
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public class BaseFlowLayout2ViewHolder {

    private SparseArray<View> views = new SparseArray<>();
    public View itemView;

    public BaseFlowLayout2ViewHolder(View itemView) {
        this.itemView = itemView;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * @param isVisible true:VISIBLE, false:INVISIBLE
     */
    public BaseFlowLayout2ViewHolder setVisible(@IdRes int viewId, boolean isVisible) {
        View view = getView(viewId);
        view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    /**
     * @param isGone true:GONE, false:VISIBLE
     */
    public BaseFlowLayout2ViewHolder setGone(@IdRes int viewId, boolean isGone) {
        View view = getView(viewId);
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
        return this;
    }

    public BaseFlowLayout2ViewHolder setText(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    public BaseFlowLayout2ViewHolder setText(@IdRes int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    public BaseFlowLayout2ViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public BaseFlowLayout2ViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseFlowLayout2ViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public BaseFlowLayout2ViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public BaseFlowLayout2ViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public BaseFlowLayout2ViewHolder setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}
