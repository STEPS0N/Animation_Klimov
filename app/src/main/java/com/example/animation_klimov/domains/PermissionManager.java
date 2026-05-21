package com.example.animation_klimov.domains;

import android.Manifest;
import android.app.Activity;
import android.app.BroadcastOptions;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.net.PortUnreachableException;

public class PermissionManager {
    public static void GetPermission(Context context) {
        if (!CheckPermissions(context)) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.CALL_PHONE
                    }, 100
            );
        }
    }

    public static boolean CheckPermissions(Context context) {
        boolean hasReadContactsPermissions = ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED;

        boolean hasCallPhonePermission = ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED;

        return hasReadContactsPermissions && hasCallPhonePermission;
    }
}
