package com.allen.sankeyview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.allen.sankeyview.model.Level;
import com.allen.sankeyview.model.Link;
import com.allen.sankeyview.model.Node;
import com.allen.sankeyview.model.SanKeyModel;
import com.allen.sankeyview.utils.DensityUtil;
import com.allen.sankeyview.utils.SanKeyUtil;

import java.util.List;

public class SanKeyView extends View {
    private static String[] defaultColors = {"#FBB367", "#80B1D2", "#FB8070", "#CC99FF", "#B0D961",
            "#99CCCC", "#BEBBD8", "#FFCC99", "#8DD3C8", "#FF9999",
            "#CCEAC4", "#BB81BC", "#FBCCEC", "#CCFF66", "#99CC66",
            "#66CC66", "#FF6666", "#FFED6F", "#ff7f50", "#87cefa"};

    private Context mContext;
    //The width of the View
    private int mWidth;
    //height of View
    private int mHeight;
    //View entity object
    private SanKeyModel mSanKeyModel;
    //Content PaddingLeft
    private int mPaddingLeft;
    //Content PaddingRight
    private int mPaddingRight;
    //Content PaddingTop
    private int mPaddingTop;
    //Content PaddingBottom
    private int mPaddingBottom;
    // width of the node
    private int nodeWidth;
    // space between legend and nodes
    private int legendPadding;
    //Brush to draw the node
    private Paint mNodePaint;
    //Brush for drawing the border of the node
    private Paint mNodeBoundPaint;
    //Brush for drawing connecting lines
    private Paint mLinkLinePaint;
    //Brush for drawing legend
    private Paint mLegendPaint;
    //The parent container of the node flow chart
    private SanKeyGroup sanKeyGroup;
    // measure whether the View is complete
    private boolean isMeasure;


    public SanKeyView(Context context) {
        this(context, null);
    }

    public SanKeyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SanKeyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mPaddingLeft = DensityUtil.dip2px(mContext, 80);
        mPaddingTop = DensityUtil.dip2px(mContext, 10);
        mPaddingRight = DensityUtil.dip2px(mContext, 80);
        mPaddingBottom = DensityUtil.dip2px(mContext, 10);
        nodeWidth = DensityUtil.dip2px(mContext, 10);
        legendPadding = DensityUtil.dip2px(mContext, 10);
        //Initialize the brush for drawing the node area
        mNodePaint = new Paint();
        mNodePaint.setAntiAlias(true);
        mNodePaint.setStyle(Paint.Style.FILL);
        //Initialize the brush that draws the boundary area of the node
        mNodeBoundPaint = new Paint();
        mNodeBoundPaint.setAntiAlias(false);
        mNodeBoundPaint.setStyle(Paint.Style.STROKE);
        mNodeBoundPaint.setColor(Color.BLACK);
        mNodeBoundPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 1));
        //Initialize the brush for drawing the connecting line
        mLinkLinePaint = new Paint();
        mLinkLinePaint.setAntiAlias(true);
        mLinkLinePaint.setColor(Color.BLUE);
        mLinkLinePaint.setAlpha(180);
        mLinkLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //Brush for drawing legend
        mLegendPaint = new Paint();
        mLegendPaint.setAntiAlias(true);
        mLegendPaint.setTextSize(DensityUtil.sp2px(mContext, 10));
        mLegendPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        calculation();
        isMeasure = true;

    }

    /**
     * Calculate the relevant data needed for drawing
     */
    private void calculation() {
        if (null != mSanKeyModel) {
            if(mSanKeyModel.isCalculation()){
                return;
            }
            long startTime = System.currentTimeMillis();
            SanKeyUtil.calculationSanKeyModel(mSanKeyModel);
            long endTime = System.currentTimeMillis();
            Log.e("tog", endTime - startTime + "ms");
            //Calculate the path drawn by the node in the View
            calculationNodePath();
            //Calculate the path of the connecting line
            calculationLinkLinePath();
            mSanKeyModel.setCalculation(true);
            invalidate();
        }
    }

  /*  @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (null != mSanKeyModel) {
            long startTime = System.currentTimeMillis();
            SanKeyUtil.calculationSanKeyModel(mSanKeyModel);
            long endTime = System.currentTimeMillis();
            Log.e("tog", endTime - startTime + "ms");
            mSanKeyModel.getNodes();
            //计算节点在View绘制的路径
            calculationNodePath();
            //计算连接线的路径
            calculationLinkLinePath();
        }
    }*/

    /**
     * Calculate the path of the node on the View
     */
    private void calculationNodePath() {
        if (null == mSanKeyModel) {
            return;
        }
        List<Level> levelList = mSanKeyModel.getLevelList();
        float maxValueAndPaddingSum = mSanKeyModel.getMaxLevelValueAndPaddingSum();
        float levelHorizontalPadding = (mWidth - mPaddingLeft - mPaddingRight) * 1.0f / (mSanKeyModel.getMaxLevel() - 1);
        float maxLevelValueSum = mSanKeyModel.getMaxLevelValueSum();
        float nodePadding = mSanKeyModel.getNodePadding();
        for (Level level : levelList) {
            List<Node> nodes = level.getNodes();
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                Point startPoint = new Point();
                float midPoint;
                if(level.getLevel() == 0) {
                    midPoint = mPaddingTop + maxValueAndPaddingSum / 2;
                }else midPoint = mPaddingTop + i * (maxLevelValueSum * 2 + nodePadding) + maxLevelValueSum;
                startPoint.x = mPaddingLeft + (level.getLevel()) * levelHorizontalPadding;
                startPoint.y = midPoint - node.getValue();
                node.setStartPoint(startPoint);
                Point endPoint = new Point();
                endPoint.x = mPaddingLeft + (level.getLevel()) * levelHorizontalPadding;
                endPoint.y = midPoint + node.getValue();
                node.setEndPoint(endPoint);
                node.setNodeHeight(endPoint.y - startPoint.y);
                Path path = new Path();
                path.moveTo(startPoint.x, startPoint.y);
                path.lineTo(startPoint.x + nodeWidth, startPoint.y);
                path.lineTo(endPoint.x + nodeWidth, endPoint.y);
                path.lineTo(endPoint.x, endPoint.y);
                path.close();
                node.setNodePath(path);;
            }
        }
    }

    /**
     * Calculate the path of the connector
     */
    private void calculationLinkLinePath() {
        if (null == mSanKeyModel) {
            return;
        }
        List<Link> links = mSanKeyModel.getLinks();
        List<Node> nodes = mSanKeyModel.getNodes();
        for (Link link : links) {
            //Set the resource node start point and end point position
            Node sourceNode = SanKeyUtil.getNodeFromNodeName(link.getSource(), nodes);
            Point sourceStartPoint = new Point();
            sourceStartPoint.x = sourceNode.getStartPoint().x + nodeWidth;
            sourceStartPoint.y = sourceNode.getStartPoint().y + sourceNode.getNodeHeight() * sourceNode.getOutputValueSum() / sourceNode.getValue();
            link.setSourceStartPoint(sourceStartPoint);
            Point sourceEndPoint = new Point();
            sourceEndPoint.x = sourceNode.getStartPoint().x + nodeWidth;
            sourceEndPoint.y = sourceNode.getStartPoint().y + sourceNode.getNodeHeight() * (sourceNode.getOutputValueSum() + link.getValue()) / sourceNode.getValue();
            link.setSourceEndPoint(sourceEndPoint);
            sourceNode.setOutputValueSum(sourceNode.getOutputValueSum() + link.getValue());
            //Set the position of the target node start and end points
            Node targetNode = SanKeyUtil.getNodeFromNodeName(link.getTarget(), nodes);
            Point targetStartPoint = new Point();
            targetStartPoint.x = targetNode.getStartPoint().x;
            targetStartPoint.y = targetNode.getStartPoint().y + targetNode.getNodeHeight() * targetNode.getInputValueSum() / targetNode.getValue();
            link.setTargetStartPoint(targetStartPoint);
            Point targetEndPoint = new Point();
            targetEndPoint.x = targetNode.getStartPoint().x;
            targetEndPoint.y = targetNode.getStartPoint().y + targetNode.getNodeHeight() * (targetNode.getInputValueSum() + link.getValue()) / targetNode.getValue();
            link.setTargetEndPoint(targetEndPoint);
            targetNode.setInputValueSum(targetNode.getInputValueSum() + link.getValue());
            Path linkLinePath = new Path();
            linkLinePath.moveTo(sourceStartPoint.x, sourceStartPoint.y);
            //Control Points for Bezier Curves
            Point point1 = new Point();
            point1.x = sourceStartPoint.x + (targetStartPoint.x - sourceStartPoint.x) * 0.25f;
            point1.y = sourceStartPoint.y;
            Point point2 = new Point();
            point2.x = sourceStartPoint.x + (targetStartPoint.x - sourceStartPoint.x) * 0.5f;
            point2.y = sourceStartPoint.y + (targetStartPoint.y - sourceStartPoint.y) * 0.5f;
            linkLinePath.quadTo(point1.x, point1.y, point2.x, point2.y);
            Point point3 = new Point();
            point3.x = sourceStartPoint.x + (targetStartPoint.x - sourceStartPoint.x) * 0.75f;
            point3.y = targetStartPoint.y;
            linkLinePath.quadTo(point3.x, point3.y, targetStartPoint.x, targetStartPoint.y);
            linkLinePath.lineTo(targetEndPoint.x, targetEndPoint.y);
            //Control Points for Bezier Curves
            Point point4 = new Point();
            point4.x = sourceStartPoint.x + (targetStartPoint.x - sourceStartPoint.x) * 0.75f;
            point4.y = targetEndPoint.y;
            Point point5 = new Point();
            point5.x = sourceStartPoint.x + (targetStartPoint.x - sourceStartPoint.x) * 0.5f;
            point5.y = sourceEndPoint.y + (targetEndPoint.y - sourceEndPoint.y) * 0.5f;
            linkLinePath.quadTo(point4.x, point4.y, point5.x, point5.y);
            Point point6 = new Point();
            point6.x = sourceStartPoint.x + (targetStartPoint.x - sourceStartPoint.x) * 0.25f;
            point6.y = sourceEndPoint.y;
            linkLinePath.quadTo(point6.x, point6.y, sourceEndPoint.x, sourceEndPoint.y);
            linkLinePath.lineTo(sourceStartPoint.x, sourceStartPoint.y);
            link.setLinkLinePath(linkLinePath);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLinkLine(canvas);
        drawNodes(canvas);
        showAllMarkerView();
    }

    public void showAllMarkerView(){
        if (null == sanKeyGroup) {
            sanKeyGroup = (SanKeyGroup) getParent();
        }
        List<Node> nodes = mSanKeyModel.getNodes();
        for (Node node : nodes) {
            Point point = new Point();
            if(node.getNodeLevel() == 0){
                point.x = node.getStartPoint().x;
            }else point.x = node.getStartPoint().x + nodeWidth;
            point.y = (int) (node.getStartPoint().y + (node.getEndPoint().y - node.getStartPoint().y) * 0.5f);
            MarkerView markerView = new MarkerView(mContext);
            markerView.refreshContent(point, mWidth, mHeight, node);
            sanKeyGroup.addView(markerView);

        }
    }

    /**
     * draw the node
     *
     * @param canvas canvas
     */
    private void drawNodes(Canvas canvas) {
        List<Node> nodes = mSanKeyModel.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            mNodePaint.setColor(Color.parseColor(defaultColors[i % defaultColors.length]));
            if (mSanKeyModel.getSelectNode() == node) {
                mNodePaint.setAlpha(255);
            } else {
                mNodePaint.setAlpha(150);
            }
            canvas.drawPath(node.getNodePath(), mNodePaint);
            canvas.drawPath(node.getNodePath(), mNodeBoundPaint);
        }

    }

    /**
     * draw
     *
     * @param canvas canvas
     */
    private void drawLinkLine(Canvas canvas) {
        List<Link> links = mSanKeyModel.getLinks();
        for (int i = 0; i < links.size(); i++) {
            Link link = links.get(i);
//            mLinkLinePaint.setColor(Color.parseColor(defaultColors[i % defaultColors.length]));
            if (mSanKeyModel.getSelectLink() == link) {
                mLinkLinePaint.setAlpha(200);
            } else {
                mLinkLinePaint.setAlpha(100);
            }
            canvas.drawPath(link.getLinkLinePath(), mLinkLinePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Point point = new Point();
                point.x = event.getX();
                point.y = event.getY();
                onActionDown(point);
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * handle click event
     *
     * @param point the point to click
     */
    private void onActionDown(Point point) {
        if (null == sanKeyGroup) {
            sanKeyGroup = (SanKeyGroup) getParent();
        }

        //todo handle click events
        Node node = inNodeRect(point);
        if (null != node) {
            mSanKeyModel.setSelectNode(node);

            invalidate();
            return;
        }
        Link link = inLinkLinePath(point);
        if (null != link) {
            mSanKeyModel.setSelectLink(link);
            invalidate();
        } else {

        }
    }

    /**
     * Determine if a point is on the path of the connecting line
     *
     * @param point The point to be judged
     * @return the connector object where the point is
     */
    private Link inLinkLinePath(Point point) {
        if (mSanKeyModel == null) {
            return null;
        }
        List<Link> links = mSanKeyModel.getLinks();
        for (Link link : links) {
            if (pointInPath(link.getLinkLinePath(), point)) {
                return link;
            }
        }
        return null;

    }

    /**
     * Determine if a point is on the node rectangle
     *
     * @param point The point to be judged
     */
    private Node inNodeRect(Point point) {
        if (mSanKeyModel == null) {
            return null;
        }
        List<Node> nodes = mSanKeyModel.getNodes();
        for (Node node : nodes) {
            if (pointInPath(node.getNodePath(), point)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Determine if the clicked point is within a certain path
     *
     * @param path path
     * @param point The point to be judged
     * @return true in path false not in path
     */
    private boolean pointInPath(Path path, Point point) {
        RectF bounds = new RectF();
        path.computeBounds(bounds, true);
        Region region = new Region();
        region.setPath(path, new Region((int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom));
        return region.contains((int) point.x, (int) point.y);
    }

    /**
     * 设置桑基图的数据
     *
     * @param model 数据实体对象
     */
    public void setData(SanKeyModel model) {
        if (null == model) {
            //TODO data is empty
            return;
        }
        this.mSanKeyModel = model;
        if(isMeasure){
            calculation();
        }
    }

}
