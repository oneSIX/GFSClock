package com.github.gfsclock.gfstimeclock;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ManualInputActivity extends AppCompatActivity {

    private int barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_badge_input);

        final Button submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText idEditText = (EditText) findViewById(R.id.editID);
                String id = idEditText.getText().toString();
                if (id.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ManualInputActivity.this);
                    builder.setTitle(getString(R.string.id_empty));
                    builder.setMessage(getString(R.string.id_empty_message));
                    String positiveText = getString(android.R.string.ok);
                    builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    barcode = Integer.parseInt(id);
                    Intent optionsScreen = new Intent(ManualInputActivity.this, ClockOptionsActivity.class);
                    optionsScreen.putExtra("barcode", barcode);
                    ManualInputActivity.this.startActivity(optionsScreen);
                }
            }
        });

    }


}