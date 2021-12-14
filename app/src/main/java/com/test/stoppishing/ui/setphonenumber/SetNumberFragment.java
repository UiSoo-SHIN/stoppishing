package com.test.stoppishing.ui.setphonenumber;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.test.stoppishing.ListViewAdapter;
import com.test.stoppishing.PhoneNumberItem;
import com.test.stoppishing.R;
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

        ListView phoneNumberLV;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        phoneNumberLV = (ListView)root.findViewById(R.id.phone_number_list);
        phoneNumberLV.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("첫째 딸", "01022929928") ;
        // 두 번째 아이템 추가.
        adapter.addItem("둘째 아들", "01022929929") ;
        // 세 번째 아이템 추가.
        adapter.addItem("세째 아들", "01022929930") ;
        // 네 번째 아이템 추가.
        adapter.addItem("막내 딸", "01022929930") ;


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}