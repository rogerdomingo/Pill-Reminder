package com.example.asifkhan.customlistview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asifkhan.customlistview.R;
import com.example.asifkhan.customlistview.models.Pill;
import com.example.asifkhan.customlistview.models.UserInfo;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter implements View.OnClickListener{
    private ArrayList<Pill> pillArrayList;
    private Context context;

    public CustomListAdapter(ArrayList<Pill> pillArrayList, Context context) {
        this.pillArrayList = pillArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pillArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.custom_list_view_layout,null);

        Pill pill = pillArrayList.get(i);
        TextView photo=(TextView)view.findViewById(R.id.photo);
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView profession=(TextView)view.findViewById(R.id.profession);

        photo.setText("" + pill.getName().charAt(0));
        name.setText(pill.getName());
        profession.setText(pill.getName());
        return view;
    }

    //file search result
    public void filterResult(ArrayList<Pill> newPillArrayList){
        pillArrayList=new ArrayList<>();
        pillArrayList.addAll(newPillArrayList);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }
}
