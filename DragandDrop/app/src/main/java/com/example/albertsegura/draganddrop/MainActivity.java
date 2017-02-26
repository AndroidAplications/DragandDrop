package com.example.albertsegura.draganddrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity
{

    LinearLayout left_layout, right_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bitmap and its OnTouchListener
        DrawingView dv = new DrawingView(this);
        dv.setOnTouchListener(new MyTouchListener());

        // Layouts and their OnDragListeners
        left_layout = (LinearLayout) findViewById(R.id.left);
        left_layout.setOnDragListener(new MyDragListener());
        right_layout = (LinearLayout) findViewById(R.id.right);
        right_layout.setOnDragListener(new MyDragListener());

        // Adding bitmap view to the layout
        left_layout.addView(dv);
    }

    private final class MyTouchListener implements View.OnTouchListener
    {
        public boolean onTouch(View view, MotionEvent motionEvent)
        {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
            {

                return true;
            }
            else
            {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener
    {
        /*Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);*/

        @Override
        public boolean onDrag(View v, DragEvent event)
        {

            switch (event.getAction())
            {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    DrawingView view = (DrawingView) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    view.setCoordinates(event.getX(), event.getY());
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

    class DrawingView extends View
    {
        Bitmap bitmap;

        float x,y;

        public DrawingView(Context context)
        {
            super(context);
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        }

        @Override
        public void onDraw(Canvas canvas)
        {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.CYAN);
            canvas.drawBitmap(bitmap, x, y, paint);  //originally bitmap draw at x=o and y=0
        }

        public void setCoordinates(float newX, float newY)
        {
            x = newX;
            y = newY;
        }
    }
}
