package com.example.inventory.ui.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentProductBinding;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigation();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFragment(ProductInfoFragment.newInstance(null));
    }

    /**
     * Este metodo carga un fragment "child" dentro de otro fragment. Para ello se utiliza el metodo getChildmanager
     */
    private void loadFragment(Fragment newInstance) {
        if (newInstance != null) {
            getChildFragmentManager().beginTransaction().replace(R.id.productContent, newInstance).commit();
        }
    }

    /**
     * Este metodo configura el listener del componente BottomNavigationView
     */
    private void initNavigation() {
        binding.bottonNavigationProduct.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_product_info:
                    loadFragment(ProductInfoFragment.newInstance(null));
                    break;
                case R.id.action_product_map:
                    loadFragment(ProductMapFragment.newInstance(null));
                    break;
                case R.id.action_product_description:
                    loadFragment(ProductDescriptionFragment.newInstance(null));
                    break;
            }

            return true;
        });
    }
}