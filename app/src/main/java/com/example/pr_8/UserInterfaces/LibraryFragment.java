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
import com.example.pr_8.MyResponse;
import com.example.pr_8.PlaceAPI;
import com.example.pr_8.PlacePost;
import com.example.pr_8.R;
import com.example.pr_8.RetrofitFactory;
import com.example.pr_8.ViewModels.BookViewPattern;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LibraryFragment extends Fragment {
    private final String URL_API = "https://jsonplaceholder.typicode.com/";
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


        try {
            setDataApi();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getDataFromApi(view);


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
    private void getDataFromApi(View view) {
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceAPI placeholderAPI = retrofit.create(PlaceAPI.class);
        Call<List> call1 = placeholderAPI.getPosts();
        Call<List> call2 = placeholderAPI.getComments();
        call1.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {
                if (response.isSuccessful()) {
                    List posts = response.body();
                    Log.d("Success", posts.get(3).toString());
                    TextView text = view.findViewById(R.id.textView5);
                    text.setText(posts.get(3).toString());
                }
                else {
                    Log.d("Hey", "Wow!");
                    return;
                }
            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {
                Log.d("Hey", "Error!");
            }
        });
        call2.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {
                if (response.isSuccessful()) {
                    List comments = response.body();
                    Log.d("Success", comments.get(3).toString());
                    TextView text = view.findViewById(R.id.textView6);
                    text.setText(comments.get(3).toString());
                }
                else {
                    Log.d("Hey", "Wow!");
                    return;
                }
            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {
                Log.d("Hey", "Error!");
            }
        });
        Log.d("Hey", "Hello!");
    }

    private void setDataApi() throws IOException {
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceAPI api = retrofit.create(PlaceAPI.class);
        PlacePost post = new PlacePost();
        post.setUserID(123);
        post.setId(34);
        post.setBody("Hello, you there");
        post.setTitle("title");
        Call<MyResponse> call = api.request(post);
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("Successful", "Send data successful");
                }
                else {
                    Log.d("Hey", "Wow!");
                    return;
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });
    }

}