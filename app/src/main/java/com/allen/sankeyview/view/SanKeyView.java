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
    //View的宽度
    private int mWidth;
    //View的高度
    private int mHeight;
    //View的实体对象
    private SanKeyModel mSanKeyModel;
    //内容PaddingLeft
    private int mPaddingLeft;
    //内容PaddingRight
    private int mPaddingRight;
    //内容PaddingTop
    private int mPaddingTop;
    //内容PaddingBottom
    private int mPaddingBottom;
    //节点的宽度
    private int nodeWidth;
    //图例和节点之间的间隔
    private int legendPadding;
    //绘制节点的画笔
    private Paint mNodePaint;
    //绘制额节点边界的画笔
    private Paint mNodeBoundPaint;
    //绘制连接线的画笔
    private Paint mLinkLinePaint;
    //绘制图例的画笔
    private Paint mLegendPaint;
    //节点流程图的父容器
    private SanKeyGroup sanKeyGroup;
    //测量View是否完成
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
        mPaddingLeft = DensityUtil.dip2px(mContext, 10);
        mPaddingTop = DensityUtil.dip2px(mContext, 10);
        mPaddingRight = DensityUtil.dip2px(mContext, 40);
        mPaddingBottom = mPaddingTop;
        nodeWidth = DensityUtil.dip2px(mContext, 10);
        legendPadding = DensityUtil.dip2px(mContext, 2);
        //初始化绘制节点区域的画笔
        mNodePaint = new Paint();
        mNodePaint.setAntiAlias(true);
        mNodePaint.setStyle(Paint.Style.FILL);
        //初始化绘制节点边界区域的画笔
        mNodeBoundPaint = new Paint();
        mNodeBoundPaint.setAntiAlias(false);
        mNodeBoundPaint.setStyle(Paint.Style.STROKE);
        mNodeBoundPaint.setColor(Color.BLACK);
        mNodeBoundPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 1));
        //初始化绘制连接线的画笔
        mLinkLinePaint = new Paint();
        mLinkLinePaint.setAntiAlias(true);
        mLinkLinePaint.setColor(Color.GRAY);
        mLinkLinePaint.setAlpha(180);
        mLinkLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //绘制图例的画笔
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
     * 计算绘制需要的相关数据
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
            mSanKeyModel.getNodes();
            //计算节点在View绘制的路径
            calculationNodePath();
            //计算连接线的路径
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
     * 计算节点在View上的路径
     */
    private void calculationNodePath() {
        if (null == mSanKeyModel) {
            return;
        }
        List<Level> levelList = mSanKeyModel.getLevelList();
        float levelHorizontalPadding = (mWidth - mPaddingLeft - mPaddingRight) * 1.0f / (mSanKeyModel.getMaxLevel() - 1);
        float unitValueHeight = (mHeight - mPaddingTop - mPaddingBottom) / mSanKeyModel.getMaxLevelValueAndPaddingSum();
        for (Level level : levelList) {
            List<Node> nodes = level.getNodes();
            //float levelVerticalPadding = (mSanKeyModel.getMaxLevelValueAndPaddingSum() - level.getValueAndPaddingSum()) * unitValueHeight * 0.5f;
            float levelVerticalPadding = 0;
            float currentValueSum = 0;
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                Point startPoint = new Point();
                startPoint.x = mPaddingLeft + (level.getLevel()) * levelHorizontalPadding;
                startPoint.y = mPaddingTop + levelVerticalPadding + currentValueSum * unitValueHeight;
                node.setStartPoint(startPoint);
                currentValueSum += node.getValue();
                Point endPoint = new Point();
                endPoint.x = mPaddingLeft + (level.getLevel()) * levelHorizontalPadding;
                endPoint.y = mPaddingTop + levelVerticalPadding + currentValueSum * unitValueHeight;
                node.setEndPoint(endPoint);
                node.setNodeHeight(endPoint.y - startPoint.y);
                Path path = new Path();
                path.moveTo(startPoint.x, startPoint.y);
                path.lineTo(startPoint.x + nodeWidth, startPoint.y);
                path.lineTo(endPoint.x + nodeWidth, endPoint.y);
                path.lineTo(endPoint.x, endPoint.y);
                path.close();
                node.setNodePath(path);
                currentValueSum += mSanKeyModel.getNodePadding();
            }
        }
    }

    /**
     * 计算连接线的路径
     */
    private void calculationLinkLinePath() {
        if (null == mSanKeyModel) {
            return;
        }
        List<Link> links = mSanKeyModel.getLinks();
        List<Node> nodes = mSanKeyModel.getNodes();
        for (Link link : links) {
            //设置资源节点开始点和结束点位置
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
            //设置目标节点开始点和结束点的位置
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
            //贝塞尔曲线的控制点
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
            //贝塞尔曲线的控制点
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
        //drawLegendText(canvas);
    }

    /**
     * 绘制每一个节点的图例文本
     *
     * @param canvas 画布
     */
    private void drawLegendText(Canvas canvas) {
        List<Node> nodes = mSanKeyModel.getNodes();
        for (Node node : nodes) {
            Point nodeCenterPoint = new Point();
            nodeCenterPoint.x = node.getStartPoint().x + nodeWidth;
            nodeCenterPoint.y = (int) (node.getStartPoint().y + (node.getEndPoint().y - node.getStartPoint().y) * 0.5f);
            Rect boundRect = new Rect();
            mLegendPaint.getTextBounds(node.getName(), 0, node.getName().length(), boundRect);
            canvas.drawText(node.getName(), nodeCenterPoint.x + legendPadding, nodeCenterPoint.y + boundRect.height() * 0.5f, mLegendPaint);
        }
    }

    /**
     * 绘制节点
     *
     * @param canvas 画布
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
     * 绘制
     *
     * @param canvas 画布
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
     * 处理点击事件
     *
     * @param point 点击的点
     */
    private void onActionDown(Point point) {
        if (null == sanKeyGroup) {
            sanKeyGroup = (SanKeyGroup) getParent();
        }
        Node node = inNodeRect(point);
        mSanKeyModel.cancelSelectNodeAndLink();
        if (null != node) {
            mSanKeyModel.setSelectNode(node);
            sanKeyGroup.showMarkView(point, mWidth, mHeight, node.getName());
            invalidate();
            return;
        }
        Link link = inLinkLinePath(point);
        if (null != link) {
            mSanKeyModel.setSelectLink(link);
            sanKeyGroup.showMarkView(point, mWidth, mHeight, link.getSource() + " > " + link.getTarget() + " " + link.getValue());
            invalidate();
        } else {
            sanKeyGroup.hideMarkView();
        }
    }

    /**
     * 判断某个点是否在连接线的路径上
     *
     * @param point 要判断的点
     * @return 点所在的连接线对象
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
     * 判断某个点是否在节点矩形上
     *
     * @param point 要判断的点
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
     * 判断点击点是否在某个路径内
     *
     * @param path  路径
     * @param point 所要判断的点
     * @return true 在路径内 false 不在路径内
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
            //TODO 数据为空
            return;
        }
        this.mSanKeyModel = model;
        if(isMeasure){
            calculation();
        }
    }

}
