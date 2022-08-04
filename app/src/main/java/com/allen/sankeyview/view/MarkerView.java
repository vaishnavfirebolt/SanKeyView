package com.allen.sankeyview.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.sankeyview.R;
import com.allen.sankeyview.model.Node;
import com.allen.sankeyview.utils.DensityUtil;


/**
 * node flow chart MarkerView
 *
 * @author Renjy
 */
public class MarkerView extends RelativeLayout {
    private Context mContext;
    private View view;
    private TextView titleTV, contentTV;
    //Animation of markerView x-axis translation
    private ObjectAnimator translationX;
    //MarkerView y-axis translation animation
    private ObjectAnimator translationY;
    //markerView panning animation collection
    private AnimatorSet animatorSet;

    public MarkerView(Context context) {
        super(context);
        this.mContext = context;
        init();


    }

    /**
     * initialization
     * 1. Add default layout
     * 2. Initialize related objects of property animation
     */
    private void init() {
        setupLayoutResource();
        translationX = new ObjectAnimator();
        translationY = new ObjectAnimator();
        animatorSet = new AnimatorSet();  //Combine animation
    }

    /**
     * Add default MarkerView layout
     */
    private void setupLayoutResource() {
        view = LayoutInflater.from(mContext).inflate(R.layout.marker_view, this);
        titleTV = findViewById(R.id.marker_x_value);
        contentTV = findViewById(R.id.marker_content);
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
    public void refreshContent(final Point point, final int width, final int height, Node node) {
        if (null != titleTV) {
            titleTV.setText(node.getName());
        }
        this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, (int) Math.max(200,(node.getEndPoint().y - node.getStartPoint().y))));
        this.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        this.layout(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        moveMarkerView(point, width, height, node);


    }

    /**
     * Move MarkerView to click point using property animation
     *  @param point the point to click
     * @param width The width of the View
     * @param height Height of View
     * @param isLevelZero
     */
    private void moveMarkerView(Point point, int width, int height, Node node) {
        //MarkerView Show the middle right of the clicked point
        int offsetX;
        int offsetY;
        int measuredWidth = this.getMeasuredWidth();
        int measuredHeight = this.getMeasuredHeight();

        int markerViewMargin = DensityUtil.dip2px(mContext, 2);
        Log.d("measured height", "moveMarkerView: " + measuredHeight + " " + node.getNodeHeight() + " " + markerViewMargin);
        //Calculate the offset of the x-axis
        if(node.getNodeLevel() == 0) {
            offsetX = (int) (point.x - measuredWidth);
        }else {
            if (point.x + measuredWidth > width - markerViewMargin) {//Determine the right side of markerView
                offsetX = (int) (point.x - measuredWidth);
            } else {
                offsetX = (int) point.x;
            }
        }


        //Calculate the offset of the y-axis
        if (point.y < (measuredHeight / 2 + markerViewMargin)) {//Calculate the top
            offsetY = markerViewMargin;
        } else if ((point.y + measuredHeight / 2) < (height - markerViewMargin)) {//Calculate the bottom
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
        animatorSet.playTogether(translationX, translationY); //Set animation
        animatorSet.setDuration(3); //Set the animation time
        animatorSet.start(); //Start
        view.setVisibility(VISIBLE);
    }

    /**
     * Hide MarkerView
     */
    public void hide() {
        view.setVisibility(View.GONE);
    }
}
