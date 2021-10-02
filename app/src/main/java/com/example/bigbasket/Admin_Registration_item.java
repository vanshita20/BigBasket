package com.example.bigbasket;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

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


      //  ImageButton productimage;
    //  String product_name,product_manufacture,product_description,product_price,product_displaydate,product_quantity,product_category;
        Details_obj details_obj;

        Uri filepath;
        Bitmap bitmap;
        String cat;
        static String imguri;

        String uid;


        FirebaseUser user;

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

        details_obj=new Details_obj();

        firebaseDatabase = FirebaseDatabase.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();



        ProductImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("product image in", "onClick: ");
                Intent gg=new Intent();
                gg.setAction(Intent.ACTION_GET_CONTENT);
                gg.setType("image/*");
                startActivityForResult(gg,1);
            }
        });

        FAuth = FirebaseAuth.getInstance();
        Add_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product_Name=ProductName.getText().toString().trim();
                Product_Description=ProductDescription.getText().toString().trim();
                Product_Price=ProductPrice.getText().toString().trim();
                Product_Quantity=ProductQuantity.getText().toString().trim();

                uploadtofirebase();
                details_obj.setName(Product_Name);

                details_obj.setProduct_description(Product_Description);
                details_obj.setProduct_price(Product_Price);
                details_obj.setProduct_quantity(Product_Quantity);
                details_obj.setImageURL(imguri);
                details_obj.setSid(uid);

                databaseReference = firebaseDatabase.getReference("product");

                databaseReference.child(cat).child(Product_Name).setValue(details_obj);
            }

        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            filepath=data.getData();
            try
            {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                ProductImage.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),categories[position],Toast.LENGTH_LONG).show();
        int i= (int) arg0.getItemIdAtPosition(position);
        cat=categories[i];
    }

    @Override
    public void onNothingSelected(android.widget.AdapterView<?> adapterView) {

    }


    private void uploadtofirebase() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference().child(Product_Name+filepath).child("img1");
        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imguri = uri.toString();

                                Log.d("onSuccess: ", "uri" +imguri);
                                details_obj.setImageURL(imguri);
                                databaseReference = firebaseDatabase.getReference("product");

                                databaseReference.child(cat).child(Product_Name).setValue(details_obj);
                            }
                        });
                        Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        float percent = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        dialog.setMessage("Uploaded :" +  percent + " %");
                    }
                });
    }


    }


