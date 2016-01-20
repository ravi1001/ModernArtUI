package com.mocca.modernartui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class ModernArtUIActivity extends ActionBarActivity {

    // declare text views that appear as colored rectangles
    private TextView mCenterRectangle;
    private TextView mTopOfCenterRectangle;
    private TextView mTopCenterRectangle;
    private TextView mTopRightRectangle;
    private TextView mLeftRectangle;
    private TextView mRightRectangle;
    private TextView mBottomtRectangle;

    // set base RGB colors
    private final int BASE_RED = 255;
    private final int BASE_GREEN = 255;
    private final int BASE_BLUE = 255;

    // declare seek bar
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art_ui);

        // get text view objects
        mCenterRectangle = (TextView) findViewById(R.id.center_view);
        mTopOfCenterRectangle = (TextView) findViewById(R.id.top_of_center_view);
        mTopCenterRectangle = (TextView) findViewById(R.id.top_center_view);
        mTopRightRectangle = (TextView) findViewById(R.id.top_right_view);
        mLeftRectangle = (TextView) findViewById(R.id.left_view);
        mRightRectangle = (TextView) findViewById(R.id.right_view);
        mBottomtRectangle = (TextView) findViewById(R.id.bottom_view);

        // get seek bar object
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);

        // set seek bar change listener
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // invoke method to update color of rectangles based on seek bar position
                updateColors(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // invoke method to set initial background colors
        setInitialColors();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modern_art_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            // create a dialog to get user choice on whether he wants to visit the MoMA website now
            AlertDialog.Builder builder = new AlertDialog.Builder(ModernArtUIActivity.this, AlertDialog.THEME_HOLO_LIGHT);

            // set dialog ok button
            builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                // handle click event
                public void onClick(DialogInterface dialog, int id) {

                    // create implicit intent to view web page
                    Intent viewWebPageIntent = new Intent();

                    // set intent action
                    viewWebPageIntent.setAction(Intent.ACTION_VIEW);

                    // set intent data
                    viewWebPageIntent.setData(Uri.parse("http://www.moma.org"));

                    // pass intent to start activity
                    startActivity(viewWebPageIntent);
                }
            });

            // set dialog cancel button
            builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                // handle click event
                public void onClick(DialogInterface dialog, int id) {
                }
            });

            // create the AlertDialog
            AlertDialog dialog = builder.create();

            // center the message
            TextView messageText = new TextView(this);
            messageText.setText(R.string.dialog_message);
            messageText.setGravity(Gravity.CENTER);

            // set the message
            dialog.setView(messageText);

            // display the dialog on screen
            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // set initial background color of rectangles
    private void setInitialColors() {
        mCenterRectangle.setBackgroundColor(Color.WHITE);
        mTopOfCenterRectangle.setBackgroundColor(Color.rgb(BASE_RED, 0, 0));
        mTopCenterRectangle.setBackgroundColor(Color.rgb(0, BASE_GREEN, 0));
        mTopRightRectangle.setBackgroundColor(Color.rgb(0, 0, BASE_BLUE));
        mLeftRectangle.setBackgroundColor(Color.rgb(BASE_RED, BASE_GREEN, 0));
        mRightRectangle.setBackgroundColor(Color.rgb(0, BASE_GREEN, BASE_BLUE));
        mBottomtRectangle.setBackgroundColor(Color.rgb(BASE_RED, 0, BASE_BLUE));
    }

    // update background color of rectangles based on seek bar position
    private void updateColors(int seekBarPosition) {
        mTopOfCenterRectangle.setBackgroundColor(Color.rgb(BASE_RED, seekBarPosition, 0));
        mTopCenterRectangle.setBackgroundColor(Color.rgb(0, BASE_GREEN, seekBarPosition));
        mTopRightRectangle.setBackgroundColor(Color.rgb(seekBarPosition, 0, BASE_BLUE));
        mLeftRectangle.setBackgroundColor(Color.rgb(BASE_RED-seekBarPosition, BASE_GREEN-seekBarPosition, seekBarPosition));
        mRightRectangle.setBackgroundColor(Color.rgb(seekBarPosition, BASE_GREEN-seekBarPosition, BASE_BLUE-seekBarPosition));
        mBottomtRectangle.setBackgroundColor(Color.rgb(BASE_RED-seekBarPosition, seekBarPosition, BASE_BLUE-seekBarPosition));
    }
}
