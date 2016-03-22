package com.example.mendez.spy;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class TelephonyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent intent) {
// TODO Auto-generated method stub
            try {
                if (intent != null && intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
//Toast.makeText(context, "Outgoign call", 1000).show();
                    String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                } else {
//get the phone state
                    String newPhoneState = intent.hasExtra(TelephonyManager.EXTRA_STATE) ? intent.getStringExtra(TelephonyManager.EXTRA_STATE) : null;
                    Bundle bundle = intent.getExtras();

                    if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
//read the incoming call number
                        String phoneNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                        Log.i("PHONE RECEIVER", "Telephone is now ringing " + phoneNumber);

//                        Intent i = new Intent(context, MainActivity.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                        startActivity(i);

                        intent.setPackage(null);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        startActivity(intent);


                    } else if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
//Once the call ends, phone will become idle
                        Log.i("PHONE RECEIVER", "Telephone is now idle");
                        intent.setPackage(null);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        startActivity(intent);

                    } else if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
//Once you receive call, phone is busy
                        Log.i("PHONE RECEIVER", "Telephone is now busy");
                    }
                }
            } catch (Exception ee) {
                Log.i("Telephony receiver", ee.getMessage());
            }
        }
    }





}



