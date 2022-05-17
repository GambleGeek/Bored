package com.ain_2_20_adis_kerimov.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.retrofit.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentRetrofit fragmentRetrofit = new FragmentRetrofit();
        ft.replace(R.id.container, fragmentRetrofit);
        ft.commit();
        setContentView(view);

    }
}