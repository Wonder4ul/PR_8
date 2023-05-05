package com.example.pr_8.UserInterfaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.pr_8.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BookMainPage extends Fragment {

    private static final String TAG = "BookMainPage";



    public BookMainPage() {
        super(R.layout.fragment_book_main_page);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_main_page, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView text1 = view.findViewById(R.id.BookName);
        String bookName = getArguments().getString("bookName");
        text1.setText("Название: " + bookName);
        TextView text2 = view.findViewById(R.id.NookAuthor);
        text2.setText("Автор: " + getArguments().getString("author"));

        List<Future<Integer>> callable = new ArrayList<>();
        ProgressBar bar = view.findViewById(R.id.progressBar);
        Callable<Integer> callableTask = () -> {
        try {
                for(int i =0; i<=100; i++){
                    int finalI = i;
                    bar.post(() -> bar.setProgress(finalI));
                    Thread.sleep(100);
                }
        } catch(InterruptedException e){
            throw new RuntimeException(e);
        }
                return 1;
        };

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_blankFragment4_to_libraryFragment));



        final ExecutorService executorService = Executors.newFixedThreadPool(2);


        Button button = view.findViewById(R.id.executorButton);
        button.setOnClickListener(view1 -> {
            callable.add(executorService.submit(callableTask));
        });
    }

}
