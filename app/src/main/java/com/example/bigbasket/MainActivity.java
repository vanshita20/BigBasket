package com.example.bigbasket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth Fauth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Fauth = FirebaseAuth.getInstance();
                if(Fauth.getCurrentUser()!=null){
                    if(Fauth.getCurrentUser().isEmailVerified()){
                        Fauth=FirebaseAuth.getInstance();

                        Intent i= new Intent(MainActivity.this,Allinone_Navigation.class);

                        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getUid()+"/Role");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                String role = snapshot.getValue(String.class);
                                Log.d( "onDataChange: ","Admin here");
                                if(role.equals("Admin")){
                                    i.putExtra("v1","Admin");
                                    startActivity(i);

                                    finish();

                                }

                                if(role.equals("Customer")){
                                    i.putExtra("v1","Customer");
                                    startActivity(i);
                                    Log.d("intent as customer", "onDataChange: ");

                                    finish();

                                }

                                if(role.equals("DeliveryPerson")){
                                    i.putExtra("v1","Delivery");
                                    startActivity(i);

                                    finish();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();

                            }
                        });
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Check Whether You Have Verified Your Detail , Otherwise Please Verify");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(MainActivity.this,MainMenu.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        Fauth.signOut();
                    }
                }else {

                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);
    }
}