package com.example.igp.igpmobile;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by vimal on 14/12/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{

    private MainActivity mainActivity;
    private TextView textView;
    private FrameLayout frameLayout;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);

        mainActivity = getActivity();
        frameLayout = (FrameLayout) mainActivity.findViewById(R.id.mainContainer);

        textView = (TextView)mainActivity.findViewById(R.id.textView);

    }

    public void testPreconditions(){
        assertNotNull("mainActivity is null", mainActivity);
        assertNotNull("textView is null", textView);
    }

    public void testMyFirstTestTextView_labelText(){
        final String expected = mainActivity.getString(R.string.app_name);
        final String actual = textView.getText().toString();
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
