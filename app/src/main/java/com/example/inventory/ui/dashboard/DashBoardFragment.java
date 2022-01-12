package com.example.inventory.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentDashBoardBinding;

public class DashBoardFragment extends Fragment implements View.OnClickListener {

    FragmentDashBoardBinding binding;

    public DashBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashBoardBinding.inflate(inflater, container,false);
        binding.imgBtnInventory.setOnClickListener(this);
        binding.imgBtnDependencies.setOnClickListener(this);
        binding.imgBtnSectors.setOnClickListener(this);
        binding.imgBtnAboutUs.setOnClickListener(this);
        binding.imgBtnProducts.setOnClickListener(this);
        binding.imgBtnSettings.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public void onClick(View v) {

    }
}