package com.gars.uiloader.library;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


/**
 * Created by Владимир on 25.06.2014.
 */
public class UILoaderController {

    // where set loader
    private ViewGroup root;
    private Activity activity;
    // view loader
    private View loader;
    private boolean isShown;
    // loader view, it can be change
    private int layout;
    // show animate
    private int showAnimate = R.anim.uiloaderfadein;
    // hide animation
    private int hideAnimate = R.anim.uiloaderfadeout;

    /**
     * Prepare loading place
     * @param activity
     */
    public UILoaderController(Activity activity) {
        this.activity  = activity;
        ViewGroup decorView = (ViewGroup) activity.findViewById(android.R.id.content);
        if(decorView!=null){
            View firstChaild = decorView.getChildAt(0);

            if(firstChaild instanceof RelativeLayout == false && firstChaild instanceof FrameLayout == false){
                FrameLayout rootViewNew = new FrameLayout(activity);
                rootViewNew.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                decorView.removeView(firstChaild);
                rootViewNew.addView(firstChaild);
                decorView.addView(rootViewNew);
                root = rootViewNew;
            } else {
                root = (ViewGroup) firstChaild;
            }
        }
    }

    /**
     * @param activity
     * @param root set another root view for loader
     */
    public UILoaderController(Activity activity, ViewGroup root) {
        this.activity  = activity;
        this.root = root;
    }

    public View getLoader(){
        return loader;
    }

    /**
     * Show view loader in front
     */
    public void show(){
        if(isShown)
            return;

        // create loader view
        if(loader == null)
            init();

        isShown = true;
        if(loader.getVisibility()!= View.VISIBLE)
            loader.setVisibility(View.VISIBLE);
        // animate visible loader
        loader.startAnimation(getAnimation(showAnimate));
    }

    /**
     * Hide loader
     */
    public void hide(){
        if(!isShown || loader == null)
            return;

        isShown = false;
        Animation animation = getAnimation(hideAnimate);
        animation.setAnimationListener(hideAnimationListener);
        loader.startAnimation(animation);
    }

    /**
     * @param showAnimate animation from resources
     */
    public void setShowAnimate(int showAnimate){
        this.showAnimate = showAnimate;
    }

    /**
     * @param hideAnimate animation from resources
     */
    public void setHideAnimate(int hideAnimate){
        this.hideAnimate = hideAnimate;
    }

    public boolean isShown() {
        return isShown;
    }

    /**
     * @param layoutRes change view loader from resources
     */
    public void setLoaderView(int layoutRes) {
        this.layout = layoutRes;
    }

    private void init() {
        loader = activity.getLayoutInflater().inflate(layout != 0 ? layout : R.layout.uiloaderview, root, false);
        if(root!=null)
            root.addView(loader);
    }

    private Animation getAnimation(int anim){
        if(loader.getAnimation()!=null)
            loader.clearAnimation();

        return AnimationUtils.loadAnimation(activity, anim);
    }

    Animation.AnimationListener hideAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {}

        @Override
        public void onAnimationEnd(Animation animation) {
            if(loader.getVisibility()!= View.GONE)
                loader.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {}
    };
}
