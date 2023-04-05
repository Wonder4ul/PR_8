package com.example.pr_8.UserInterfaces;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.pr_8.R;
import com.example.pr_8.ViewModels.BookViewPattern;
import com.example.pr_8.ViewModels.RegistrationViewPattern;

public class RegistrationFragment extends Fragment {

    private RegistrationViewPattern registrationView;

    public RegistrationFragment() {
        super(R.layout.fragment_registration);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationView = new ViewModelProvider(this).get(RegistrationViewPattern.class);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int randomNumber = (int)(Math.random()*100000);
        TextView ticket = view.findViewById(R.id.ticket);
        ticket.setText(String.valueOf(randomNumber));
        EditText text = view.findViewById(R.id.editTextTextPersonName);
        assert getArguments() != null;
        if(getArguments().getBoolean("checker")){
            String str = getArguments().getString("key2");
            Toast.makeText(getContext(), "Вы выбрали книгу " + str, Toast.LENGTH_SHORT).show();
        }



        Button button2 = view.findViewById(R.id.buttonToList2);
        button2.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("key1", String.valueOf(text.getEditableText()));
            Navigation.findNavController(view1).navigate(R.id.action_blankFragment2_to_blankFragment3, bundle);
        });


        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view1 -> {
            registrationView.createAppSpec(requireContext(), "RegistrationData",
                    R.id.editTextTextPersonName + " " + R.id.editTextTextPersonName2);

            registrationView.createExternal(requireActivity(), "RegistrationData",
                    R.id.editTextTextPersonName + " " + R.id.editTextTextPersonName2);

            registrationView.createSharedPreferences(requireContext(), "RegistrationData",
                    R.id.editTextTextPersonName + " " + R.id.editTextTextPersonName2);
        });

    }
}