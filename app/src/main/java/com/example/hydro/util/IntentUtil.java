package com.example.hydro.util;

import android.content.Context;
import android.content.Intent;


public class IntentUtil {


    public static Intent createIntent(Context packageContext, Class<?> cls) {
        return new Intent(packageContext, cls);
    }
}
