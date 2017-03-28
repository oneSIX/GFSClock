package com.github.gfsclock.gfstimeclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



public class ScanBadgeActivity extends AppCompatActivity {

    private String barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_badge);
    }

    /**
     * The scanBadge1() is called when pressing the Scan badge button, the IntentIntegrator
     * provides intent functionality of the zxing camera scanner module.
     *
     * @param view
     */
    public void scanBadge1(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }


    // TODO refactor this into menu overflow / inflator
    /**
     * The admin() method is called when passing intent to the admin configuration screen.
     *
     * @param view
     */
    public void admin(View view){
        Intent pinScreen = new Intent(ScanBadgeActivity.this, PinToAdminActivity.class);
        ScanBadgeActivity.this.startActivity(pinScreen);
    }

    /**
     * The manual() method is called when passign intent to the manual badge input screen.
     * Since the manual input is another activity control will be passed directly to it.
     * @param view
     */
    public void manual(View view){
        Intent manualEntry = new Intent(ScanBadgeActivity.this, ManualInputActivity.class);
        ScanBadgeActivity.this.startActivity(manualEntry);
    }


    /**
     * The onActivityResult function responds to intents that were started in the ScanBadgeActivity activity.
     * This is called automatically from the result of the IntentIntegrator and can be matched to
     * either a successful scan or a canceled scan.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("ScanBadgeActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("ScanBadgeActivity", "Scanned");
                barcode = result.getContents().substring(1);

                Toast.makeText(this, "Scanned: " + barcode, Toast.LENGTH_LONG).show();
                processScan();
//                startActivity(options, scanResults);
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
            Toast.makeText(this, "something goofed", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * The processScan() is called after a succesful scan is returned from the scanBadge1() method.
     * This passes the contents of the scanned barcode to the ClockOptionsActivity.
     */
    public void processScan(){
        Intent optionsScreen = new Intent(ScanBadgeActivity.this, ClockOptionsActivity.class);
        optionsScreen.putExtra("barcode", barcode);
        ScanBadgeActivity.this.startActivity(optionsScreen);
    }

}
