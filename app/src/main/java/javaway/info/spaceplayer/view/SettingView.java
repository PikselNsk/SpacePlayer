package javaway.info.spaceplayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import flow.Flow;
import javaway.info.spaceplayer.R;
import javaway.info.spaceplayer.flow.RootScreen;
import javaway.info.spaceplayer.flow.SettingScreen;

/**
 * Created by максим on 14.10.2017.
 */

public class SettingView extends LinearLayout implements IView {

    public static final int SPLASH_STATE = 0;
    public static final int LOGIN_STATE = 1;

    @BindView(R.id.big_blue_iv)
    ImageView mBigImageView;

    private SettingScreen mMySettingScreen;
    private int mState;

    public SettingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode()){
            //do something
            mMySettingScreen = Flow.getKey(this);
            mState  = mMySettingScreen.getState();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        if(!isInEditMode()){
            //do something
        }
        mBigImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flow.get(SettingView.this).set(new RootScreen());
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(!isInEditMode()){

        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(!isInEditMode()){

        }
    }

    @Override
    public boolean viewOnBackPressed() {
        return false;
    }
}
