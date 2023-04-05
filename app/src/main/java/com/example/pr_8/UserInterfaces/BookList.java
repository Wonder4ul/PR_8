package com.example.pr_8.UserInterfaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr_8.BookRecycleAdapter;
import com.example.pr_8.R;
import com.example.pr_8.ViewModels.BookViewPattern;

public class BookList extends Fragment {


    private BookViewPattern bookViewPattern;
    public BookList() {
        super(R.layout.fragment_book_list);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookViewPattern = new ViewModelProvider(this).get(BookViewPattern.class);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        String str = getArguments().getString("key1");
        Toast.makeText(getContext(), "Добро пожаловать, " + str, Toast.LENGTH_SHORT).show();

        BookRecycleAdapter.OnBookClickListener bookClickListener = (state, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("bookName", state.getName());
            bundle.putString("author", "Somebody");
            bundle.putAll(bundle);
            Navigation.findNavController(view).navigate(R.id.action_blankFragment3_to_blankFragment4, bundle);
        };
        BookRecycleAdapter adapter = new BookRecycleAdapter(getContext(), bookClickListener);
        recyclerView.setAdapter(adapter);
        bookViewPattern.books.observe(getViewLifecycleOwner(), adapter::update);
    }







}
