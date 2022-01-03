package com.orinab.shoporinab.interfaces;

import android.content.Intent;

public interface SmsBroadcastReceiverListener {

    void onSuccess(Intent intent);

    void onFailure();
}