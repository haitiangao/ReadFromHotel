package com.example.readfromhotel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.readfromhotel.R;
import com.example.readfromhotel.adapter.ClerkRecycleAdapter;
import com.example.readfromhotel.model.Guest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private List<Guest> guestList = new ArrayList<Guest>();

    @BindView(R.id.clerkRecycleView)
    RecyclerView recycleClerkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recycleClerkView.setLayoutManager(new LinearLayoutManager(this));
        recycleClerkView.setAdapter(new ClerkRecycleAdapter(guestList));
        recycleClerkView.addItemDecoration(itemDecoration);

        readFromContentProvider();
        refreshView();




    }




    private void readFromContentProvider() {

        String uri = "content://com.example.persistenthotel.provider.HotelContentProvider/guests";

        ContentResolver contentResolver = getContentResolver();
        Cursor guestCursor = contentResolver.query(Uri.parse(uri), null, null, null, null);

        guestCursor.moveToPosition(-1);
        guestList.clear();
        Log.d("TAG_H", "Checking: " + guestList );

        while (guestCursor.moveToNext()) {

            String guestPrefix = guestCursor.getString(guestCursor.getColumnIndex("guest_prefix"));
            String guestName = guestCursor.getString(guestCursor.getColumnIndex("guest_name"));
            String date = guestCursor.getString(guestCursor.getColumnIndex("date_made"));
            String hotelNumber = guestCursor.getString(guestCursor.getColumnIndex("hotel_number"));


            guestList.add(new Guest(guestPrefix,guestName,date, hotelNumber));

            Log.d("TAG_H", "From Provider : " + guestName);
        }
        Log.d("TAG_H", "Checking: " + guestList.get(0).getActualName());

        guestCursor.close();
    }

    private void refreshView(){

        ClerkRecycleAdapter recycleAdaptor = new ClerkRecycleAdapter(guestList);
        recycleClerkView.setAdapter(null);
        recycleClerkView.setAdapter(recycleAdaptor);
        recycleAdaptor.notifyDataSetChanged();

    }


    protected void onResume(){
        super.onResume();
        readFromContentProvider();
        //refreshView();
    }


}
