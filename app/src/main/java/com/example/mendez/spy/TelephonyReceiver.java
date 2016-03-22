package com.example.mendez.spy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;



import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by mendez on 16/03/16.
 */
public class TelephonyReceiver extends BroadcastReceiver {


    private static Context myContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        myContext = context;
        String action = intent.getAction();
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
                    Log.i("PHONE RECEIVERR", "Telephone is now ringing " + phoneNumber);

                    Intent i;
                    i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);


                } else if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
//Once the call ends, phone will become idle
                    Log.i("PHONE RECEIVERR", "Telephone is now idle");
//                    Intent dial = new Intent();
//                    dial.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//                    context.startActivity(dial);
                    Intent i;
                    i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);


                } else if (newPhoneState != null && newPhoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
//Once you receive call, phone is busy
                    Log.i("PHONE RECEIVERR", "Telephone is now busy");
                    Intent i;
                    i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }

            }

        } catch (Exception ee) {
            Log.i("Telephony receiver", ee.getMessage());
        }
    }

}
