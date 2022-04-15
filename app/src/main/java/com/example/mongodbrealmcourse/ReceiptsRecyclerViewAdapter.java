package com.example.mongodbrealmcourse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mongodbrealmcourse.data.model.Receipts;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ReceiptsRecyclerViewAdapter extends RecyclerView.Adapter<ReceiptsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Receipts> ReceiptsList= new ArrayList<>();
    private Context context;
    public ReceiptsRecyclerViewAdapter(Context context)
    {
        this.context = context;

    }
    public ReceiptsRecyclerViewAdapter()
    {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ProductPrice.setText( ReceiptsList.get(position).getPrice());
        holder.ProuductName.setText(ReceiptsList.get(position).getName());






    }

    @Override
    public int getItemCount() {
        return ReceiptsList.size();
    }

    public void setReceiptsList(ArrayList<Receipts> receiptsList) {
        ReceiptsList = receiptsList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView ProuductName;
        private TextView ProductPrice;
        private ImageView Img;
        public ViewHolder(@NotNull View itemView){
            super(itemView);
            ProuductName = itemView.findViewById(R.id.product_name);
            ProductPrice = itemView.findViewById(R.id.product_price);
            Img = itemView.findViewById(R.id.imageView2);



        }

    }
}
