package com.example.hotelbooking.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelbooking.R;
import com.example.hotelbooking.activities.user_activities.UserDetailHotelActivity;
import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.model.HotelFilter;
import com.example.hotelbooking.model.Room;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class SearchHotelAdapter extends RecyclerView.Adapter<SearchHotelAdapter.viewHolder> implements Filterable {
    private List<Hotel> hotels;
    private List<Hotel> hotelsFiltered;
    private HotelFilter hotelFilter;

    public SearchHotelAdapter(List<Hotel> hotels, HotelFilter hotelFilter) {
        this.hotels = hotels;
        this.hotelsFiltered = hotels;
        this.hotelFilter = hotelFilter;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_hotel, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Hotel model = hotelsFiltered.get(position);
        holder.tvName.setText(model.getName());
        holder.tvPrice.setText(model.getPrice());
        holder.tvAddress.setText(model.getAddress());
        Glide.with(holder.hotelImage.getContext()).load(model.getPurl()).into(holder.hotelImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserDetailHotelActivity.class);
                intent.putExtra("HotelKey", model.getKey());
                intent.putExtra("HotelId", model.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    public void changeData(List<Hotel> hotels) {
        this.hotels.clear();
        this.hotels.addAll(hotels);
        this.hotelsFiltered.clear();
        this.hotelsFiltered.addAll(hotels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return hotelsFiltered.size();
    }

    private boolean checkRoomType(Hotel hotel) {
        for (Room room : hotel.getRooms()) {
            switch (hotelFilter.getRoomType()) {
                case SINGLE:
                    return room.getType().equals("Single");

                case DOUBLE:
                    return room.getType().equals("Double");

                case TRIPLE:
                    return room.getType().equals("Triple");

                default:
                    return false;
            }
        }
        return false;
    }

    private boolean checkRoomPrice(Hotel hotel) {
        for (Room room : hotel.getRooms()) {
            int price = Integer.parseInt(room.getPrice());
            switch (hotelFilter.getRoomPrice()) {
                case F10_T30:
                    return price > 10 && price < 30;
                case F30_T50:
                    return price > 30 && price < 50;
                case F50_T70:
                    return price > 50 && price < 70;
                case UP70:
                    return price > 70;
            }
        }
        return false;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    hotelsFiltered = hotels;
                } else {
                    List<Hotel> filteredList = new ArrayList<>();
                    for (Hotel row : hotels) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    hotelsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = hotelsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                hotelsFiltered = (ArrayList<Hotel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        ImageView hotelImage;
        TextView tvName, tvPrice, tvAddress;
        FirebaseAuth mAuth;
        FirebaseFirestore fStore;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            hotelImage = itemView.findViewById(R.id.hotelImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvAddress = itemView.findViewById(R.id.tvStatus);

            mAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();

        }

    }
}
