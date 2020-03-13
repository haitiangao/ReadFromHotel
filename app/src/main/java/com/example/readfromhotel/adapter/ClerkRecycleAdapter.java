package com.example.readfromhotel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readfromhotel.R;
import com.example.readfromhotel.model.Guest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClerkRecycleAdapter extends RecyclerView.Adapter<ClerkRecycleAdapter.ClerkViewHolder> {

    private List<Guest> guestList;

    public ClerkRecycleAdapter(List<Guest> guestList) {
        this.guestList = guestList;
    }



    @NonNull
    @Override
    public ClerkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clerk_layout_item, parent, false);
        return new ClerkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClerkRecycleAdapter.ClerkViewHolder holder, int position) {
        holder.fullNameTextView.setText(String.format("%s %s", guestList.get(position).getPrefix(), guestList.get(position).getActualName()));
        holder.hotelNumber.setText(String.format("Room: %s", guestList.get(position).getRoomNumber()));

    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }



    class ClerkViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.full_Name_View)
        TextView fullNameTextView;

        @BindView(R.id.hotel_Number)
        TextView hotelNumber;

        public ClerkViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
