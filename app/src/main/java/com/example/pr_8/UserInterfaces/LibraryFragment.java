package com.example.pr_8.UserInterfaces;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr_8.Book;
import com.example.pr_8.ListAdapters.BookRecycleAdapter;
import com.example.pr_8.R;
import com.example.pr_8.ViewModels.BookViewPattern;


public class LibraryFragment extends Fragment {

    private BookViewPattern viewModel;
    private BookViewPattern bookViewPattern;

    private BookRecycleAdapter bookRecycleAdapter;
    public LibraryFragment() {
        super(R.layout.fragment_library);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(BookViewPattern.class);
        Intent intent = requireActivity().getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/*".equals(type)) {
                ((TextView)getView().findViewById(R.id.textView)).setText(intent.getStringExtra(Intent.EXTRA_TEXT));
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bookRecycleAdapter = new BookRecycleAdapter(requireContext(), (book, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("bookName", book.getName());
            bundle.putString("author", "Somebody");
            bundle.putAll(bundle);
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setAdapter(bookRecycleAdapter);
        viewModel.mBooks.observe(getViewLifecycleOwner(), bookList -> bookRecycleAdapter.update(bookList));




        Button transferButton = view.findViewById(R.id.data_transfer_button);
        transferButton.setOnClickListener(view1 -> {
            String cords = "88005553535";
            Uri map = Uri.parse("sms:" + cords);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, map);
            mapIntent.putExtra(Intent.EXTRA_TEXT, "its easier to call than to borrow from someone");
            try {
                startActivity(mapIntent);
            } catch (ActivityNotFoundException e) {
                e.getStackTrace();
            }});

    }
}