package com.example.animation_klimov.presentations;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.animation_klimov.R;

import java.net.URI;

public class ActivityContact extends AppCompatActivity {

    ImageView ivPhoto;
    TextView tvName;
    TextView tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);

        ivPhoto = findViewById(R.id.ivPhoto);
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);

        String name = getIntent().getStringExtra("name");
        String contactId = getIntent().getStringExtra("contactId");
        String photoUri = getIntent().getStringExtra("photoUri");
        String fallbackPhone = getIntent().getStringExtra("phone");

        tvName.setText(name);

        if (photoUri != null && !photoUri.isEmpty()){
            ivPhoto.setImageURI(Uri.parse(photoUri));
        }

        StringBuilder allPhones = new StringBuilder();

        if (contactId != null && !contactId.isEmpty()) {
            Cursor cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{contactId},
                    null
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String phone = cursor.getString(0);
                    if (allPhones.length() > 0) allPhones.append("\n");
                    allPhones.append(phone);
                }
                cursor.close();
            }
        }

        tvPhone.setText(allPhones.toString());
    }
}