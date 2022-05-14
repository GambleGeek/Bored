package com.example.retrofit;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.databinding.ActivityMainBinding;
import com.example.retrofit.databinding.FragmentRetrofitBinding;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentRetrofit extends Fragment {

    TextView name, type, link, price;
    ImageView favourite;
    private FragmentRetrofitBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init();
        createActivity();
        clickListen();
        return view;
    }

    public void init(){
        name = binding.name;
        type = binding.type;
        link = binding.link;
        price = binding.price;
        favourite = binding.like;
    }

    public void clickListen(){
        binding.btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createActivity();
            }
        });
    }

    public void createActivity(){
        Call<Activity> call = RetrofitClass.getInstance().getActivity();
        call.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                // код 200
                if (response.isSuccessful()) {
                    // код для успешного случая
                    Activity activity = response.body();
                    name.setText(activity.getActivity());
                    type.setText(activity.getType());
                    link.setText(activity.getLink());
                    price.setText(activity.getPrice() + "$");
                } else {
                    switch(response.code()) {
                        case 404:
                            // страница не найдена. можно использовать ResponseBody, см. ниже
                            break;
                        case 500:
                            // ошибка на сервере. можно использовать ResponseBody, см. ниже
                            break;
                    }
                    // или
                    // Также можете использовать ResponseBody для получения текста ошибки
                    ResponseBody errorBody = response.errorBody();
                    try {
                        name.setText(errorBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
            }
        });
    }

}