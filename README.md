# UILoader
Show loader above content

<h2>Quick start</h2>

```java
public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaderController = new UILoaderController(this);

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
```
