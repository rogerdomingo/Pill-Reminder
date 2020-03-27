package com.example.asifkhan.customlistview.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.SQLiteHelpers.SQLiteDBHelper;
import com.example.asifkhan.customlistview.adapters.CustomListAdapter;
import com.example.asifkhan.customlistview.models.Pill;
import com.example.asifkhan.customlistview.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private ArrayList<UserInfo> userInfos;
    private CustomListAdapter customListAdapter;
    private ListView customListView;
    private BottomNavigationView mBottomNavigationView;
    private Pill pill1 = new Pill("Pill1");


    private ArrayList<Pill> arrayListPills;
    private User user;

    private SQLiteDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new SQLiteDBHelper(this);
        String userEmail = getIntent().getStringExtra("USER_EMAIL");
        user = db.getUser(userEmail);

        arrayListPills = new ArrayList<>();
        arrayListPills = db.getAllPills(user);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        customListView=(ListView)findViewById(R.id.custom_list_view);

        FloatingActionButton fab = findViewById(R.id.add_pill_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddPill.class);
                intent.putExtra("USER_EMAIL", user.getEmail());
                startActivityForResult(intent, 0);
            }
        });

        customListAdapter = new CustomListAdapter(arrayListPills,this);
        customListView.setAdapter(customListAdapter);

        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ViewPillActivity.class);
                //intent.addFlags();
                intent.putExtra("PILL_NAME", arrayListPills.get(i).getName());
                intent.putExtra("PILL_DATE", arrayListPills.get(i).getName());
                startActivityForResult(intent, 0);
            }
        });

        // Initialize and assign variable
        mBottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNavigation);
        // Set Home Selected
        mBottomNavigationView.setSelectedItemId(R.id.menu_home);
        // Perform item selected listener
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_panic_btn:
                        startActivity(new Intent(getApplicationContext(), PanicButtonActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_home:
                        return true;
                    case R.id.menu_tracker:
                        startActivity(new Intent(getApplicationContext(), MapActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Taaaaaag", "Tag");
        arrayListPills = new ArrayList<>();
        arrayListPills = db.getAllPills(user);

        customListAdapter = new CustomListAdapter(arrayListPills,this);
        customListView.setAdapter(customListAdapter);
        customListAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_option,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText=newText.toString();
                ArrayList<Pill> newPillArrayList = new ArrayList<>();
                for(Pill pill:arrayListPills){
                    String name=pill.getName().toLowerCase();
                    if(name.contains(newText)){
                        newPillArrayList.add(pill);
                    }
                }
                customListAdapter.filterResult(newPillArrayList);
                customListAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
