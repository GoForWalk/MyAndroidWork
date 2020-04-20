package com.lec.android.a008_practice;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> implements Serializable {

    List<Model> items = new ArrayList<Model>();

    static ModelAdapter adapter;

    public ModelAdapter() {this.adapter = this;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());

        View itemView = inf.inflate(R.layout.address_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvAge, tvAddress;
        ImageButton btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddress = itemView.findViewById(R.id.tvAddress);

            btnDelete = itemView.findViewById(R.id.btnDelete);


            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.removeItem(getAdapterPosition());
                    adapter.notifyDataSetChanged();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();

                    Intent intent = new Intent(v.getContext(), ModelDetail.class);

                    intent.putExtra("ad", adapter.getItem(position));

                    v.getContext().startActivity(intent);

                    // TODO 인텐트 보내기

                    return false;
                }
            });


        }// end 생성자

        public void setItem(Model item){
            tvName.setText("이름: " + item.getName());
            tvAddress.setText("주소: " + item.getAddress());
            tvAge.setText("나이: " + item.getAge());
        }



    } // end ViewHolder

    public void addItem(Model item){items.add(item);}
    public void addItem(int position, Model item){items.add(position,item);}
    public Model getItem(int position){ return items.get(position);}
    public void removeItem(int position){items.remove(position);}


    public void setItems(ArrayList<Model> items){this.items = items;}
//    public void setItems(int position, Model items){ items.set(position,item);}



} // end ModelAdapter
