package com.boe.searchbar;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by HLB on 2017/3/22.
 */
public class SearchBarWidget extends LinearLayout {
    private Button mSearchCancelButton;  //取消按钮
    private ImageView mSearchRightImageView;
    private EditText mSearchEditText;  //搜索内容

    private onSearchListener mOnSearchListener = null;
    public interface onSearchListener
    {
        public void onSearchChange(String search);
    }

    public SearchBarWidget(Context context)
    {
        super(context);
        Log.e("TAG","SearchBarWidget 一个参数");
        viewInit(context);
        logicInit();
    }

    public SearchBarWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        Log.e("TAG","SearchBarWidget 两个个参数");
        viewInit(context);
        logicInit();
    }

    /*** 界面初始化 **/
    private void viewInit(Context context)
    {
        inflate(context,R.layout.search_bar_layout, this);
        mSearchCancelButton = (Button) findViewById(R.id.search_cancel_button);
        mSearchRightImageView = (ImageView)findViewById(R.id.search_right);
        mSearchEditText = (EditText)findViewById(R.id.search_text);
    }

    /*** 逻辑初始化 **/
    private void logicInit()
    {
        if(mSearchCancelButton != null)
        {
            mSearchCancelButton.setOnClickListener(mSearchCancelClickListener);
        }

        if(mSearchEditText != null)
        {
            mSearchEditText.setOnTouchListener(mSearchEditTextOnClickListener);
            mSearchEditText.addTextChangedListener(mSearchTextWatcher);
        }
        setTextEditable(false);
    }

    /** 取消键点击事件处理 **/
    private View.OnClickListener mSearchCancelClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(mSearchCancelButton != null && mSearchCancelButton.getVisibility() == View.VISIBLE)
            {
                mSearchCancelButton.setVisibility(GONE);
                setSearchBarState(LAYOUT_STATE_VIEW);
            }
        }
    };

    /** EditText Touch事件处理 **/
    private View.OnTouchListener mSearchEditTextOnClickListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            if(mSearchCancelButton!= null && mSearchCancelButton.getVisibility() != View.VISIBLE)
            {
                setSearchBarState(LAYOUT_STATE_EDIT);
            }
            return false;
        }
    };

    /** 搜索条文字变化监听器 ***/
    private TextWatcher mSearchTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            if(mOnSearchListener != null)
            {
                mOnSearchListener.onSearchChange(s.toString());
            }
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }
        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
        }
    };

    /***
     * 设置搜索框是否可以编辑
     * @param isEditable
     */
    private void setTextEditable(boolean isEditable)
    {
        if(isEditable)
        {
            mSearchEditText.setFocusableInTouchMode(true);
            mSearchEditText.setFocusable(true);
            mSearchEditText.requestFocus();
        }
        else
        {

            mSearchEditText.clearFocus();
            mSearchEditText.setFocusable(false);
        }
    }

    private static final int LAYOUT_STATE_VIEW = 1;
    private static final int LAYOUT_STATE_EDIT = 2;
    /**
     * 设置搜索条的状态
     * <p>浏览状态 LAYOUT_STATE_VIEW 只显示搜索条 同时失去焦点</p>
     * <p>编辑状态 LAYOUT_STATE_EDIT 显示搜索条和取消按钮 获取焦点</p>
     * @param state
     */
    private void setSearchBarState(int state)
    {
        switch (state) {
            case LAYOUT_STATE_VIEW:
                mSearchEditText.setText("");
                mSearchCancelButton.setVisibility(View.GONE);
                mSearchCancelButton.startAnimation(getButtonTranslateAnimation(false,300));
                mSearchRightImageView.startAnimation(getImageTranslateAnimation(false, 0));
                setTextEditable(false);
                break;
            case LAYOUT_STATE_EDIT:
                mSearchCancelButton.setVisibility(View.VISIBLE);
                mSearchCancelButton.startAnimation(getButtonTranslateAnimation(true, 100));
                mSearchRightImageView.startAnimation(getImageTranslateAnimation(true, 0));
                setTextEditable(true);

                break;

            default:
                break;
        }
    }

    /*** 取消按鈕位移動畫**/
    private TranslateAnimation getButtonTranslateAnimation(boolean in ,int distance)
    {
        TranslateAnimation animation = null;
        if(in)
        {

            animation =  new TranslateAnimation(distance,0 , 0, 0);
        }
        else
        {
            animation =  new TranslateAnimation(0,distance, 0, 0);
        }
        animation.setDuration(300);
        animation.setFillAfter(true);
        return animation;
    }

    /*** 背景存图位移动画 **/
    private TranslateAnimation getImageTranslateAnimation(boolean in,int distance)
    {
        TranslateAnimation animation = null;
        if(in)
        {
            animation =  new TranslateAnimation(0, -distance, 0, 0);
        }
        else
        {
            animation =  new TranslateAnimation(-distance, 0, 0, 0);
        }
        animation.setDuration(300);
        animation.setFillAfter(true);
        return animation;
    }

    public void setOnSearchListener(onSearchListener listener)
    {
        if(listener != null)
        {
            mOnSearchListener = listener;
        }
    }

}
