package com.example.bigbasket;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Admin_fragment extends Fragment {
    FloatingActionButton add_product;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_admin_fragment, container, false);
        // Inflate the layout for this fragment
        add_product=(FloatingActionButton) view.findViewById(R.id.floting_add);
        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),Admin_Registration_item.class);
                startActivity(intent);
            }
        });
        return view;
    }
}