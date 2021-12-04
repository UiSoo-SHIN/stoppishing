package com.test.stoppishing.ui.setphonenumber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.test.stoppishing.databinding.FragmentSetPhoneNumberBinding;

public class SetNumberFragment extends Fragment {

    private SetNumberViewModel setNumberViewModel;
    private FragmentSetPhoneNumberBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setNumberViewModel =
                new ViewModelProvider(this).get(SetNumberViewModel.class);

        binding = FragmentSetPhoneNumberBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSetPhoneNumber;
        setNumberViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}