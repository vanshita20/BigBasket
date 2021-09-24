package com.example.bigbasket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Registration_item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView ProductImage;
    EditText ProductName, ProductDescription,ProductQuantity,ProductPrice;

    Button Add_Product;
    Spinner ProductCategories;

    String Product_Image,Product_Name, Product_Description,Product_Quantity,Product_Price,add_product,Product_Categories;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    String[] categories = {"Fruits","Vegetables","Dairy Product","Refined  Oil and Sauces","Cereal","Beverages","Household Supplies","Personal Care","Baking Goods"};

    private Object AdapterView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__registration_item);

        ProductImage = (ImageView) findViewById(R.id.ProductIcon);
        ProductName = (EditText) findViewById(R.id.title);
        ProductDescription = (EditText) findViewById(R.id.description);
        ProductQuantity = (EditText) findViewById(R.id.quantity);
        ProductPrice = (EditText) findViewById(R.id.Price);
        Add_Product = (Button) findViewById(R.id.AddProduct);
        ProductCategories = (Spinner) findViewById(R.id.category);


        ProductCategories.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ProductCategories.setAdapter(aa);


        FAuth = FirebaseAuth.getInstance();
        Add_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product_Name=ProductName.getText().toString().trim();
                Product_Description=ProductDescription.getText().toString().trim();
                Product_Price=ProductPrice.getText().toString().trim();
                Product_Quantity=ProductQuantity.getText().toString().trim();
                

            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),categories[position],Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(android.widget.AdapterView<?> adapterView) {

    }
}