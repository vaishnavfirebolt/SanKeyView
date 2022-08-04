package com.allen.sankeyview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.allen.sankeyview.model.SanKeyModel;

/**
 * Node flowchart layout (node graph + MarkerView)
 *
 * @author Renjy
 */
public class SanKeyGroup extends FrameLayout {
    private Context mContext;
    private SanKeyView sanKeyView;

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
     *initialization
     * Add node graph and MarkerView
     */
    private void init() {
        removeAllViews();
        sanKeyView = new SanKeyView(mContext);
        addView(sanKeyView);
    }

    /**
     * Set the data of the node graph
     *
     * @param sanKeyModel node flowchart data
     */
    public void setData(SanKeyModel sanKeyModel) {
        init();
        sanKeyView.setData(sanKeyModel);
    }
}
