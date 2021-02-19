package com.weiyh.loginview.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.weiyh.loginview.MainActivity;
import com.weiyh.loginview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NextFragment extends Fragment {

    private FragmentTransaction transaction;
    private MainActivity mainActivity;
    private Fragment nextFragment;
    private Fragment loginFragment;

    private View view;
    private EditText editPhone;
    private Button btnNext;


    public NextFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_next, container, false);

//        btnNext = view.findViewById(R.id.btn_next);
//        editPhone = view.findViewById(R.id.edit_phone);

//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginFragment loginFragment = new LoginFragment();
//                loginFragment.setPhone(editPhone.getText().toString());
//            }
//        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        btnNext = view.findViewById(R.id.btn_next);

//        NextFragment nextFragment = new NextFragment();
//        Bundle bundle = new Bundle();
//        Log.d("nextFragment", editPhone.getText().toString());
//        bundle.putString("phone", editPhone.getText().toString());
//        nextFragment.setArguments(bundle);

    }
}
