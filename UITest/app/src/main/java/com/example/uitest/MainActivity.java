package com.example.uitest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="UITest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        //允许使用非功能区
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            lp.layoutInDisplayCutoutMode= WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        //全屏显示(隐藏状态栏)
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        //沉浸式
        View decorView = getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        systemUiVisibility |= flags;
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);

        setContentView(R.layout.activity_main);
        //尽量不要在OnCreate中获取view的参数设置，因为此时view还没有绘制完成，无法取得真实值

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获得屏幕宽高
        //有一个坑，就是getMetrics获取的高度不准确，要得到准确的高度需要使用getRealMetrics
        //原因：Android4.4之前，getMetrics是准确的，Android4.4之后，得到的高度会忽略底部的虚拟按键

        DisplayMetrics metrics=new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        }
        Log.d(TAG,"real screen resolution:"+metrics.widthPixels+","
                +metrics.heightPixels);




        //下面的函数都是在API28

        Rect rect=new Rect();
        //获得view所attach的window的大小，不受异形屏和非异形屏影响
        //分屏之后会影响window的大小
        //总而言之该函数就是获得view所绑定的window的位置大小
        //一般window的左上角不是0,0，所以应该一般window不包含状态栏
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        Log.d(TAG,"decro_view_visible_display_frame:"+rect);


//
//
//        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getWindowVisibleDisplayFrame(rect);
//        Log.d(TAG,"content_view_display_frame:"+rect);
//
//        getWindow().findViewById(R.id.text_view_2).getWindowVisibleDisplayFrame(rect);
//        Log.d(TAG,"text_view_2_display_frame:"+rect);


//        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rect);
//        Log.d(TAG,"content_view_drawing_rect:"+rect);
//
//        getWindow().findViewById(R.id.text_view).getDrawingRect(rect);
//        Log.d(TAG,"text_view_drawing_rect:"+rect);
//
//        getWindow().findViewById(R.id.text_view_2).getDrawingRect(rect);
//        Log.d(TAG,"text_view_2_drawing_rect:"+rect);
//
//        getWindow().findViewById(R.id.text_view_3).getDrawingRect(rect);
//        Log.d(TAG,"text_view_3_drawing_rect:"+rect);

        //涉及到view的坐标一般都是相对parent的坐标
//        int left=findViewById(R.id.text_view_3).getLeft();
//        int right=findViewById(R.id.text_view_3).getRight();
//        int top=findViewById(R.id.text_view_3).getTop();
//        int bottom=findViewById(R.id.text_view_3).getBottom();



//        Log.d(TAG,"text_view3_left:"+left);
//        Log.d(TAG,"text_view3_top:"+top);
//        Log.d(TAG,"text_view3_right:"+right);
//        Log.d(TAG,"text_view3_bottom:"+bottom);
//        findViewById(R.id.text_view_3).scrollTo(-1000,-1000);
        findViewById(R.id.linear_layout).scrollTo(-100,-100);

//        int scrollX=findViewById(R.id.text_view_3).getScrollX();
//        Log.d(TAG,"text view 3 scrollX:"+scrollX);
//
//        findViewById(R.id.text_view_3).setTranslationX(100);
//        int translationX=(int)findViewById(R.id.text_view_3).getTranslationX();
//        Log.d(TAG,"text view 3 trans x:"+translationX);

//        findViewById(R.id.linear_layout).getDrawingRect(rect);
//        Log.d(TAG,"linear layout drawing rect"+rect);
//
//        findViewById(R.id.linear_layout).getLocalVisibleRect(rect);
//        Log.d(TAG,"linear layout local visible:"+rect);
//
//        findViewById(R.id.linear_layout).getGlobalVisibleRect(rect);
//        Log.d(TAG,"linear layout global visible:"+rect);






        //actionBar高度,如果使用getActionBar会导致NPE
//        int actionBarHeight=getSupportActionBar().getHeight();
//        Log.d(TAG,"action_bar_height:"+actionBarHeight);
        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Rect rect=new Rect();
        findViewById(R.id.linear_layout).getDrawingRect(rect);
        Log.d(TAG,"linear layout drawing rect"+rect);

        findViewById(R.id.linear_layout).getLocalVisibleRect(rect);
        Log.d(TAG,"linear layout local visible:"+rect);

        findViewById(R.id.linear_layout).getGlobalVisibleRect(rect);
        Log.d(TAG,"linear layout global visible:"+rect);


        int[] res={0,0};

        //这里很奇怪，LocationOnScreen和InWindow是一样的，按道理window向下有一个偏移
        findViewById(R.id.linear_layout).getLocationOnScreen(res);
        Log.d(TAG,"linear layout location on screen:"+res[0]+","+res[1]);

        int[] res2={0,0};
        findViewById(R.id.linear_layout).getLocationInWindow(res2);
        Log.d(TAG,"linear layout location in window:"+res2[0]+","+res2[1]);

        findViewById(R.id.text_view).getLocationOnScreen(res2);
        Log.d(TAG,"text view location in window:"+res2[0]+","+res2[1]);

        findViewById(R.id.text_view_2).getLocationOnScreen(res2);
        Log.d(TAG,"text view2 location in window:"+res2[0]+","+res2[1]);

        int actionBarHeight=getSupportActionBar().getHeight();
        Log.d(TAG,"action_bar_height:"+actionBarHeight);

        int decro_width=getWindow().getDecorView().getWidth();
        int decro_height=getWindow().getDecorView().getHeight();

        Log.d(TAG,"decor view width,height:"+decro_width+","+decro_height);


        super.onWindowFocusChanged(hasFocus);
    }
}
