package com.example.bitcoinwalletapp.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitcoinwalletapp.BuildConfig;
import com.example.bitcoinwalletapp.R;
import com.example.bitcoinwalletapp.databinding.FragmentBtcInputBinding;
import com.example.bitcoinwalletapp.network.NetworkClient;
import com.example.bitcoinwalletapp.repository.AppRepository;
import com.example.bitcoinwalletapp.repository.AppRepositoryImpl;
import com.example.bitcoinwalletapp.viewmodel.AppViewModel;

import retrofit2.Callback;

public class BtcInputFragment extends Fragment {

    private FragmentBtcInputBinding binding;
    private AppViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_btc_input, container, false);

        // Pass the network service to AppRepositoryImpl
        AppRepository appRepository = new AppRepositoryImpl(NetworkClient.getNetworkService(BuildConfig.API_KEY));
        viewModel = new AppViewModel(appRepository, (CurrencyConversionsFragment) getParentFragment());

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        // Set click listener for the update button
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.makeApiCall();
            }
        });

        return binding.getRoot();
    }
}
