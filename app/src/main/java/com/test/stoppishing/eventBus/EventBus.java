package com.test.stoppishing.eventBus;

import android.os.Looper;
import com.squareup.otto.Bus;
import android.os.Handler;
import android.util.Log;


public class EventBus extends Bus {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        Log.e("TEST", "getInstance is called");
        return BUS;
    }

    private EventBus() {
    }

    @Override
    public void register(Object object) {
        Log.e("TEST", "register is called");
        super.register(object);
    }

    @Override
    public void unregister(Object object) {
        Log.e("TEST", "unregister is called");
        super.register(object);
    }

    @Override
    public void post(Object event) {
        Log.e("TEST", "post is called");
        super.post(event);
    }
}