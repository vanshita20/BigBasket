package com.example.bigbasket;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class Admin_fragment extends Fragment {
    FloatingActionButton add_product;

    FirebaseDatabase firebaseDatabase;
    RecyclerView Fruits,Vegetables,DairyProduct,RefinedOilandSauces,Cereal,Beverages,HouseholdSupplies,PersonalCare,BakingGoods;
    private static Recycler_adapter adapterFruits,adapterVegetables,adapterDairyProduct,adapterRefinedOilandSauces,adapterCereal,adapterBeverages,adapterHouseholdSupplies,adapterPersonalCare,adapterBakingGoods;



    LinearLayoutManager HorizontalLayout1,HorizontalLayout2,HorizontalLayout3,HorizontalLayout4,HorizontalLayout5,HorizontalLayout6,HorizontalLayout7,HorizontalLayout8,HorizontalLayout9;


    String uid;


    FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_admin_fragment, container, false);
        // Inflate the layout for this fragment
        add_product=(FloatingActionButton) view.findViewById(R.id.floting_add);

        firebaseDatabase = FirebaseDatabase.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),Admin_Registration_item.class);
                startActivity(intent);
            }
        });
        Fruits = (RecyclerView) view.findViewById(R.id.Recycler_admin_fruit);
        Vegetables= (RecyclerView) view.findViewById(R.id.Recycler_admin_Vegetables);
        DairyProduct= (RecyclerView) view.findViewById(R.id.Recycler_admin_DairyProduct);

        RefinedOilandSauces= (RecyclerView) view.findViewById(R.id.Recycler_admin_RefinedOilandSauces);
        Cereal= (RecyclerView) view.findViewById(R.id.Recycler_admin_Cereal);
        Beverages= (RecyclerView) view.findViewById(R.id.Recycler_admin_Beverages);
        HouseholdSupplies= (RecyclerView) view.findViewById(R.id.Recycler_admin_Householdsuppliments);
        PersonalCare= (RecyclerView) view.findViewById(R.id.Recycler_admin_PersonalCare);
        BakingGoods= (RecyclerView) view.findViewById(R.id.Recycler_admin_BakingGoods);
        HorizontalLayout1 = new LinearLayoutManager(getContext());
        HorizontalLayout2 = new LinearLayoutManager(getContext());
        HorizontalLayout3 = new LinearLayoutManager(getContext());
        HorizontalLayout4 = new LinearLayoutManager(getContext());
        HorizontalLayout5 = new LinearLayoutManager(getContext());
        HorizontalLayout6 = new LinearLayoutManager(getContext());
        HorizontalLayout7 = new LinearLayoutManager(getContext());
        HorizontalLayout8 = new LinearLayoutManager(getContext());
        HorizontalLayout9 = new LinearLayoutManager(getContext());


        Fruits.setLayoutManager(HorizontalLayout1);
        Vegetables.setLayoutManager(HorizontalLayout2);
        DairyProduct.setLayoutManager(HorizontalLayout3);
        RefinedOilandSauces.setLayoutManager(HorizontalLayout4);
        Cereal.setLayoutManager(HorizontalLayout5);
        Beverages.setLayoutManager(HorizontalLayout6);
        HouseholdSupplies.setLayoutManager(HorizontalLayout7);
        PersonalCare.setLayoutManager(HorizontalLayout8);
        BakingGoods.setLayoutManager(HorizontalLayout9);





        FirebaseRecyclerOptions<Model> optionsFruits =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Fruits").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        adapterFruits=new Recycler_adapter(optionsFruits);
        Fruits.setAdapter(adapterFruits);
        FirebaseRecyclerOptions<Model> optionsVegetables =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Vegetables").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        adapterVegetables=new Recycler_adapter(optionsVegetables);
        Vegetables.setAdapter(adapterVegetables);
        FirebaseRecyclerOptions<Model> optionsDairyProduct =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Dairy Product").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        adapterDairyProduct=new Recycler_adapter(optionsDairyProduct);
        DairyProduct.setAdapter(adapterDairyProduct);

        FirebaseRecyclerOptions<Model> optionsRefinedOilandSauces =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Refined Oil and Sauces").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        adapterRefinedOilandSauces=new Recycler_adapter(optionsRefinedOilandSauces);
        RefinedOilandSauces.setAdapter(adapterRefinedOilandSauces);
        FirebaseRecyclerOptions<Model> optionsCereal =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Cereal").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        adapterCereal=new Recycler_adapter(optionsCereal);
        Cereal.setAdapter(adapterCereal);
        FirebaseRecyclerOptions<Model> optionsBeverages =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Beverages").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        adapterBeverages=new Recycler_adapter(optionsBeverages);
        Beverages.setAdapter(adapterBeverages);
        FirebaseRecyclerOptions<Model> optionsPersonalCare =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Personal Care").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        adapterPersonalCare=new Recycler_adapter(optionsPersonalCare);
        PersonalCare.setAdapter(adapterPersonalCare);
        FirebaseRecyclerOptions<Model> optionsBakingGoods =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Baking Goods").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        adapterBakingGoods=new Recycler_adapter(optionsBakingGoods);
        BakingGoods.setAdapter(adapterBakingGoods);
        FirebaseRecyclerOptions<Model> optionsHouseholdSupplies =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product").child("Household Supplies").orderByChild("sid").startAt(uid).endAt("\ufaff"), Model.class)
                        .build();
        adapterHouseholdSupplies=new Recycler_adapter(optionsHouseholdSupplies);
        HouseholdSupplies.setAdapter(adapterHouseholdSupplies);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        adapterFruits.startListening();
        adapterVegetables.startListening();
        adapterDairyProduct.startListening();
        adapterRefinedOilandSauces.startListening();
        adapterCereal.startListening();
        adapterBeverages.startListening();
        adapterHouseholdSupplies.startListening();
        adapterPersonalCare.startListening();
        adapterBakingGoods.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterVegetables.stopListening();
        adapterDairyProduct.stopListening();
        adapterRefinedOilandSauces.stopListening();
        adapterCereal.stopListening();
        adapterBeverages.stopListening();
        adapterHouseholdSupplies.stopListening();
        adapterPersonalCare.stopListening();
        adapterBakingGoods.stopListening();

    }
}

//.orderByChild("sid").startAt(uid).endAt("\ufaff"),