package com.example.bigbasket;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Cart_Recycler_adapter extends FirebaseRecyclerAdapter<Cart_model,Cart_Recycler_adapter.Myholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    int p=0;
    public Cart_Recycler_adapter(@NonNull FirebaseRecyclerOptions<Cart_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Myholder myholder, int i, @NonNull Cart_model cart_model) {
        myholder.name_text.setText(cart_model.getProduct_name());
        myholder.price_text.setText(cart_model.getProduct_price());
        Glide.with(myholder.img_view.getContext()).load(cart_model.getImageURL()).into(myholder.img_view);
        Log.d("onBindViewHolder: ",String.valueOf(p));
        p+=Integer.parseInt(cart_model.getProduct_price());

        myholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                Intent inti=new Intent(activity,Confirm_order.class);
              /*  inti.putExtra("name",c.getName());
                inti.putExtra("manufacture",model.getAbout_manufacture());
                inti.putExtra("expiry_date",model.getExpiry_date());
                inti.putExtra("imageURL",model.getImageURL());
                inti.putExtra("product_description",model.getProduct_description());
                inti.putExtra("product_price",model.getProduct_price());
                inti.putExtra("sid",model.getSid());*/
                activity.startActivity(inti);
            }
        });
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card2,parent,false);
        return new Myholder(view);  }

    public class Myholder extends RecyclerView.ViewHolder {
        ImageView img_view;
        TextView name_text,price_text;
        CardView cardView;
        public Myholder(@NonNull View itemView) {

            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cust_card);
            img_view=(ImageView) itemView.findViewById(R.id.cust_image);
            name_text=(TextView) itemView.findViewById(R.id.pro_name);
            price_text=(TextView) itemView.findViewById(R.id.pro_price);
        }
    }
}
