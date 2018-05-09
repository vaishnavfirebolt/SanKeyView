package com.allen.sankeyview.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.sankeyview.R;
import com.allen.sankeyview.utils.DensityUtil;


/**
 * 节点流程图的MarkerView
 *
 * @author Renjy
 */
public class MarkerView extends RelativeLayout {
    private Context mContext;
    private View view;
    private TextView titleTV, contentTV;
    //markerView x轴平移的动画
    private ObjectAnimator translationX;
    //markerView y轴方向平移的动画
    private ObjectAnimator translationY;
    //markerView 平移动画集合
    private AnimatorSet animatorSet;

    public MarkerView(Context context) {
        super(context);
        this.mContext = context;
        init();


    }

    /**
     * 初始化
     * 1、添加默认布局
     * 2、初始化属性动画的相关对象
     */
    private void init() {
        setupLayoutResource();
        translationX = new ObjectAnimator();
        translationY = new ObjectAnimator();
        animatorSet = new AnimatorSet();  //组合动画
    }

    /**
     * 添加默认的MarkerView布局
     */
    private void setupLayoutResource() {
        view = LayoutInflater.from(mContext).inflate(R.layout.marker_view, this);
        titleTV = findViewById(R.id.marker_x_value);
        contentTV = findViewById(R.id.marker_content);
        view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.bringToFront();
        view.requestFocus();
        invalidate();
    }

    /**
     * 设置markView的文本和显示位置
     *
     * @param markText mark显示的文本
     * @param width    节点图的宽度
     * @param height   节点图的高度
     */
    public void refreshContent(final Point point, final int width, final int height, String markText) {
        if (null != titleTV) {
            titleTV.setText(markText);
        }
        this.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        this.layout(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        moveMarkerView(point, width, height);


    }

    /**
     * 使用属性动画移动MarkerView到点击点
     *
     * @param point  点击的点
     * @param width  View的宽度
     * @param height View的高度
     */
    private void moveMarkerView(Point point, int width, int height) {
        //MarkerView 显示点击点的右边中间
        int offsetX;
        int offsetY;
        int measuredWidth = this.getMeasuredWidth();
        int measuredHeight = this.getMeasuredHeight();
        int markerViewMargin = DensityUtil.dip2px(mContext, 5);
        //计算x轴的偏移量
        if (point.x + measuredWidth > width - markerViewMargin) {//判断markerView的右边
            offsetX = (int) (point.x - measuredWidth);
        } else {
            offsetX = (int) point.x;
        }

        //计算y轴的偏移量
        if (point.y < (measuredHeight / 2 + markerViewMargin)) {//计算上边
            offsetY = markerViewMargin;
        } else if ((point.y + measuredHeight / 2) < (height - markerViewMargin)) {//计算下边
            offsetY = (int) (point.y - measuredHeight / 2);
        } else {
            offsetY = height - markerViewMargin - measuredHeight;
        }
        if (offsetX < markerViewMargin) {
            offsetX = markerViewMargin;
        }
        if (offsetY < markerViewMargin) {
            offsetY = markerViewMargin;
        }
        translationX = ObjectAnimator.ofFloat(view, "translationX", offsetX, offsetX);
        translationY = ObjectAnimator.ofFloat(view, "translationY", offsetY, offsetY);
        animatorSet.playTogether(translationX, translationY); //设置动画
        animatorSet.setDuration(3);  //设置动画时间
        animatorSet.start(); //启动
        view.setVisibility(VISIBLE);
    }

    /**
     * 隐藏MarkerView
     */
    public void hide() {
        view.setVisibility(View.GONE);
    }
}
