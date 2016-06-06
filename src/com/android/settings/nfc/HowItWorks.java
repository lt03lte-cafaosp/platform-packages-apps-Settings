package com.android.settings.nfc;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.settings.R;
public class HowItWorks extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.nfc_payment_how_it_works_for_lanscape);
        } else {
            setContentView(R.layout.nfc_payment_how_it_works);
        }
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Button gotIt = (Button) findViewById(R.id.nfc_how_it_works_button);
        gotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

}
