package javaway.info.spaceplayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import flow.Flow;
import javaway.info.spaceplayer.R;
import javaway.info.spaceplayer.flow.SettingScreen;

/**
 * Created by максим on 16.10.2017.
 */

public class RootView extends LinearLayout implements IView{

    @BindView(R.id.settings_button)
    Button mSettingsButton;

    public RootView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        if(!isInEditMode()){
            //do something
        }

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flow.get(RootView.this).set(new SettingScreen(SettingView.LOGIN_STATE));
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
