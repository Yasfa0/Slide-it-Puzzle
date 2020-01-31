package com.example.pkk_sip;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

public class BearList extends ArrayAdapter {
    private Activity con;
    private List<Player> bearlist;

    public BearList(Activity con, List<Player> list) {
        super(con, R.layout.bear_list, list);
        this.con = con;
        this.bearlist = list;
    }

    @NonNull
    @Override
    public View getView(int position, View cview, ViewGroup parent) {
        LayoutInflater inflater = con.getLayoutInflater();

        View ListViewBear = inflater.inflate(R.layout.bear_list, null, true);

        ImageView icon_rank = ListViewBear.findViewById(R.id.iconRank);
        TextView txt_nama = ListViewBear.findViewById(R.id.nama);
        TextView txt_waktu = ListViewBear.findViewById(R.id.waktu);
        TextView txt_skor = ListViewBear.findViewById(R.id.skor);
        TextView txt_ukuran = ListViewBear.findViewById(R.id.ukuran);

        Player bear = bearlist.get(position);

        if (position == 0){
            icon_rank.setBackgroundResource(R.drawable.medal_gold);
        }else if (position == 1){
            icon_rank.setBackgroundResource(R.drawable.medal_silver);
        }else if (position == 2){
            icon_rank.setBackgroundResource(R.drawable.medal_bronze);
        }else if(position >= 3 && position <= 9){
            icon_rank.setBackgroundResource(R.drawable.small_star);
        }

        txt_nama.setText(bear.getNama());
        txt_skor.setText(String.valueOf(bear.getSkor()));
        txt_ukuran.setText(bear.getUkuran());
        txt_waktu.setText(bear.getTime());

        return ListViewBear;
    }
}
