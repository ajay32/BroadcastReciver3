package com.hackingbuzz.broadcastreciver3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

// Dynamic way of using broadcast reciever  ...on purpose when there is a situation where we required broadcast receiver we use it..

public class MainActivity extends Activity  {
    private static final String TAG = "MainActivity";
    private MusicIntentReceiver myReceiver;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new MusicIntentReceiver();
    }

    @Override public void onResume() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        // for registering reciver the procedure is same as we do in manifest file...we tell we tell class name in reciver tag <reciver> and action in intent <action>
        registerReceiver(myReceiver, filter);  // class object, action
        super.onResume();
    }

    private class MusicIntentReceiver extends BroadcastReceiver {
        @Override public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                    //    Log.d(TAG, "Headset is unplugged");
                        Toast.makeText(context, "Headset is unplugged",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                    //    Log.d(TAG, "Headset is plugged");
                        Toast.makeText(context, "Headset is plugged",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Log.d(TAG, "I have no idea what the headset state is");
                        Toast.makeText(context, "This will not execute coz our HeadsetPlug intent has only two state 0 and 1 ",Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override public void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }
}