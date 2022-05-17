package com.ain_2_20_adis_kerimov.retrofit;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ain_2_20_adis_kerimov.retrofit.databinding.ActivityMainBinding;
import com.ain_2_20_adis_kerimov.retrofit.databinding.FragmentRetrofitBinding;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
//        Retrofit retrofit = new retrofit2.Retrofit.Builder()
//                .baseUrl("https://www.boredapi.com/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Retrofit retrofit = RetrofitClass.getRetrofit();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Activity> call = jsonPlaceHolderApi.getActivity();
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
                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    switch(response.code()) {
                        case 404:
                            // страница не найдена. можно использовать ResponseBody, см. ниже
                            Toast.makeText(requireContext(), "Page not found!", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            // ошибка на сервере. можно использовать ResponseBody, см. ниже
                            Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show();
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

