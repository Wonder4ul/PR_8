package com.example.pr_8.ViewModels;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegistrationViewPattern extends ViewModel {

    public void createAppSpec(Context context, String fileName, String fileContent) {
        try (FileOutputStream fos = context.openFileOutput(fileName + ".txt", Context.MODE_PRIVATE)) {
            fos.write(fileContent.getBytes());
            Toast.makeText(context, "File created in " + context.getDataDir(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
