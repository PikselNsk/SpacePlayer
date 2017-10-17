package javaway.info.spaceplayer.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import flow.Flow;
import javaway.info.spaceplayer.R;
import javaway.info.spaceplayer.flow.RootScreen;
import javaway.info.spaceplayer.flow.TreeKeyDispatcher;

public class RootActivity extends AppCompatActivity  {

    @BindView(R.id.container_root)
    FrameLayout mContainerFrame;


    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = Flow.configure(newBase, this)
                .defaultKey(new RootScreen())
                .dispatcher(new TreeKeyDispatcher(this))
                .install();
        super.attachBaseContext(newBase);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {

        if(getCurrentScreen() != null && !getCurrentScreen().viewOnBackPressed() && !Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }

    public IView getCurrentScreen() {
        return (IView) mContainerFrame.getChildAt(0);
    }
}
