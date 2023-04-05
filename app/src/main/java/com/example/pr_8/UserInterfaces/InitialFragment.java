package com.example.pr_8.UserInterfaces;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.pr_8.R;

public class InitialFragment extends Fragment {

    public InitialFragment() {
        super(R.layout.fragment_initial);
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_blankFragment_to_blankFragment2));

        Button buttonLib = view.findViewById(R.id.lib_button);
        buttonLib.setOnClickListener(view12 -> Navigation.findNavController(view12).navigate(R.id.action_blankFragment_to_libraryFragment));

        ImageView image = view.findViewById(R.id.bookImage1);
        image.setImageDrawable(getResources().getDrawable(R.drawable.book_svgrepo_com));

    }
}