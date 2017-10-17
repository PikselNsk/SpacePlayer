package javaway.info.spaceplayer.flow;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Collections;
import java.util.Map;

import flow.Direction;
import flow.Dispatcher;
import flow.KeyChanger;
import flow.KeyDispatcher;
import flow.MultiKey;
import flow.State;
import flow.Traversal;
import flow.TraversalCallback;
import javaway.info.spaceplayer.R;

public class TreeKeyDispatcher implements Dispatcher, KeyChanger {

    private Activity mActivity;
    private Object inKey;
    @Nullable
    private Object outKey;
    private FrameLayout mRootLayout;

    public TreeKeyDispatcher(Activity activity) {
        mActivity = activity;
    }



    @Override
    public void dispatch(@NonNull Traversal traversal, @NonNull TraversalCallback callback) {
        Map<Object, Context> contextMap;
        State instate = traversal.getState(traversal.destination.top());
        inKey = instate.getKey();
        State outState = traversal.origin == null ? null : traversal.getState(traversal.origin.top());
        outKey   = outState == null ? null : outState.getKey();

        mRootLayout = (FrameLayout) mActivity.findViewById(R.id.container_root);

        if(inKey.equals(outKey)){
            callback.onTraversalCompleted();
            return;
        }

        if(inKey instanceof MultiKey){

        }

        Context flowContext = traversal.createContext(inKey, mActivity);
//        Context mortarContext = ScreenScoper.getScreenScope((AbstractScreen) inKey).createContext(flowContext);
        contextMap = Collections.singletonMap(inKey, flowContext);
        changeKey(outState, instate, traversal.direction, contextMap, callback);
    }

    @Override
    public void changeKey(@Nullable State outgoingState,
                          @NonNull State incomingState,
                          @NonNull Direction direction,
                          @NonNull Map<Object, Context> incomingContexts,
                          @NonNull TraversalCallback callback) {
        Context context = incomingContexts.get(inKey);

        //save prev view
        if(outgoingState != null){
            outgoingState.save(mRootLayout.getChildAt(0));
        }

//        @LayoutRes final int layout;
//        if(inKey instanceof RootScreen){
//            layout = R.layout.root_view;
//        } else if (inKey instanceof SettingScreen) {
//            layout = R.layout.view_setting;
//        } else {
//            throw new AssertionError("Unregonized screen " + inKey);
//        }

        Screen screen = inKey.getClass().getAnnotation(Screen.class);
        if(screen == null){
            throw new IllegalStateException("@Screen annotation is missing on screen " +
                    inKey.getClass().getName());
        } else {
            int layout = screen.value();

            LayoutInflater inflater = LayoutInflater.from(context);
            View newView = inflater.inflate(layout, mRootLayout, false);

            incomingState.restore(newView);

            if (mRootLayout.getChildAt(0) != null) {
                mRootLayout.removeView(mRootLayout.getChildAt(0));
            }

            mRootLayout.addView(newView);
            callback.onTraversalCompleted();
        }
    }
}
