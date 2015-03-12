package com.gars.uiloader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gars.uiloader.library.UILoaderController;


public class MainActivity extends Activity implements View.OnClickListener {

    private UILoaderController loaderController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaderController = new UILoaderController(this);

        findViewById(R.id.showLoader).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(loaderController.isShown())
            return;
        // show loder
        loaderController.show();

        // Execute hide loader after 2 seconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                loaderController.hide();
            }
        }, 2000);
    }
}
