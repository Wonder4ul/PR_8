package com.example.pr_8.ViewModels;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;

import android.Manifest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegistrationViewPattern extends ViewModel {
    private static final int REQUEST = 1;
    public void createAppSpec(Context context, String fileName, String fileContent) {
        try (FileOutputStream fos = context.openFileOutput(fileName + ".txt", Context.MODE_PRIVATE)) {
            fos.write(fileContent.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createExternal(Activity activity, String fileName, String fileContent) {
        Context context = activity.getApplicationContext();
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            File filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(filePath, fileName + ".txt");
            FileOutputStream osteream;
            try {
                osteream = new FileOutputStream(file); osteream.write(fileContent.getBytes()); osteream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

}
