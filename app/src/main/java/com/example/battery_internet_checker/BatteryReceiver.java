package com.example.battery_internet_checker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.battery_checker.R;


public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        TextView statusLabel = ((MainActivity)context).findViewById(R.id.statusLabel);
        TextView percentageLabel = ((MainActivity)context).findViewById(R.id.percentageLabel);
        ImageView batteryImage = ((MainActivity)context).findViewById(R.id.batteryImage);
        ProgressBar progressBar = ((MainActivity)context).findViewById(R.id.progreebar);

        String action = intent.getAction();

        if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {

            // Status
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            switch (status) {
                case BatteryManager.BATTERY_STATUS_FULL:
                    statusLabel.setText("Full");
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    statusLabel.setText("Charging");
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    statusLabel.setText("Discharging");
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    statusLabel.setText("Not charging");
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    statusLabel.setText("Unknown");
                    break;
            }


            // Percentage
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int percentage = level * 100 / scale;
            percentageLabel.setText(percentage + "%");
            progressBar.setProgress(percentage);


            // Image
            Resources res = context.getResources();

            if (percentage >= 90) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.bbbbb));

            } else if (90 > percentage && percentage >= 65) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.bbbb));

            } else if (65 > percentage && percentage >= 40) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.bbb));

            } else if (40 > percentage && percentage >= 15) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.bb));

            } else {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b));

            }

        }

    }
}
