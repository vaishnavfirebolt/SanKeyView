package com.allen.sankeyview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.allen.sankeyview.model.SanKeyModel;

/**
 * 节点流程图布局（节点图+ MarkerView）
 *
 * @author Renjy
 */
public class SanKeyGroup extends FrameLayout {
    private Context mContext;
    private SanKeyView sanKeyView;
    private MarkerView markerView;

    public SanKeyGroup(Context context) {
        this(context, null);
    }

    public SanKeyGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SanKeyGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    /**
     * 初始化
     * 添加节点图和MarkerView
     */
    private void init() {
        removeAllViews();
        sanKeyView = new SanKeyView(mContext);
        markerView = new MarkerView(mContext);
        markerView.setVisibility(View.GONE);
        addView(sanKeyView);
        addView(markerView);
    }

    /**
     * 设置节点图的数据
     *
     * @param sanKeyModel 节点流程图的数据
     */
    public void setData(SanKeyModel sanKeyModel) {
        init();
        sanKeyView.setData(sanKeyModel);
    }

    /**
     * 显示MarkerView
     *
     * @param point      点击的屏幕的位置点
     * @param width      节点图的宽度
     * @param height     节点图的高度
     * @param markerText markerView 要显示的文本
     */
    public void showMarkView(Point point, int width, int height, String markerText) {
        markerView.setVisibility(View.GONE);
        markerView.refreshContent(point, width, height, markerText);
    }

    /**
     * 隐藏MarkerView
     */
    public void hideMarkView() {
        markerView.hide();
    }
}
