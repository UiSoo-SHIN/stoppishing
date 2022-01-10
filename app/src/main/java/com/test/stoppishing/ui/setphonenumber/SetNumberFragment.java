package com.test.stoppishing.ui.setphonenumber;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.otto.Subscribe;
import com.test.stoppishing.ListViewAdapter;
import com.test.stoppishing.R;
import com.test.stoppishing.databinding.FragmentSetPhoneNumberBinding;
import com.test.stoppishing.eventBus.EventBus;
import com.test.stoppishing.ui.DBHelper;

public class SetNumberFragment extends Fragment {

    private SetNumberViewModel setNumberViewModel;
    private FragmentSetPhoneNumberBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setNumberViewModel =
                new ViewModelProvider(this).get(SetNumberViewModel.class);

        binding = FragmentSetPhoneNumberBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DBHelper mDBHelper = new DBHelper(getContext());
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        EventBus.getInstance().register(this);

        //DB reset, for test
        //mDBHelper.onUpgrade(db, 0, 1);


        ListView phoneNumberLV;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        phoneNumberLV = (ListView)root.findViewById(R.id.phone_number_list);
        phoneNumberLV.setAdapter(adapter);

        ImageView addBtn = (ImageView)root.findViewById(R.id.registNumber);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //TODO
                //Create dialog and insert data into database
                //init dialog
                Dialog registDialog = new Dialog(container.getContext());

                //remove title bar
                registDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                //dialog connect to xml file
                registDialog.setContentView(R.layout.regist_number_dialog);

                //Show Dialog
                registDialog.show();

                EditText nameTxt    = (EditText)registDialog.findViewById(R.id.iput_name);
                EditText numberTxt  = (EditText)registDialog.findViewById(R.id.iput_phone_number);

                //Call contract 작업중////////////////////////////////////////
                Button loadContactDataBtn = registDialog.findViewById(R.id.call_contact);
                loadContactDataBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        ActivityCompat.startActivityForResult(getActivity(), intent, 0, null);



                    }
                });

                ///////////////////////////////////////////


                Button addBtn = registDialog.findViewById(R.id.addBtn);
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(container.getContext(), "ImageView is clicked", Toast.LENGTH_SHORT).show();
                        String name          = nameTxt.getText().toString();
                        String phone_number  = numberTxt.getText().toString();

                        if(name.length() == 0 || phone_number.length() ==0){
                            Toast.makeText(container.getContext(), "공백이 입력되었습니다. 이름 또는 전화번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            ContentValues values = new ContentValues();
                            values.put(DBHelper.FeedEntry.COLUMN_NAME_NAME, name);
                            values.put(DBHelper.FeedEntry.COLUMN_NAME_NUMBER, phone_number);
                            db.insert(DBHelper.FeedEntry.TABLE_NAME, null, values);

                            refreshFragment();
                            registDialog.dismiss();

                        }
                    }
                });

                Button cancelBtn = registDialog.findViewById(R.id.cancelBtn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registDialog.dismiss();
                    }
                });
            }
        });



        //load Data
        @SuppressLint("Recycle")Cursor c = db.rawQuery("SELECT * FROM " + DBHelper.FeedEntry.TABLE_NAME, null);
        while(c.moveToNext()){
            int name_idx = c.getColumnIndex(DBHelper.FeedEntry.COLUMN_NAME_NAME);
            int number_idx = c.getColumnIndex (DBHelper.FeedEntry.COLUMN_NAME_NUMBER);
            adapter.addItem(c.getString(name_idx), c.getString(number_idx));
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getInstance().unregister(this);
        binding = null;
    }

    public void refreshFragment(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }


    @Subscribe
    public void getPost(String data) {
        Log.d("TEST", "getPost is called");
        Log.d("TEST", "data = " + data);
    }


}