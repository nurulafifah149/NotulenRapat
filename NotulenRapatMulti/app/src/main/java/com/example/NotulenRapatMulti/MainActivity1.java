package com.example.NotulenRapatMulti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.NotulenRapatMulti.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.NotulenRapatMulti.adapter.Adapter;
import com.example.NotulenRapatMulti.app.AppController;
import com.example.NotulenRapatMulti.data.Data;
import com.example.NotulenRapatMulti.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity1 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    Button btn_logout;
    TextView txt_id, txt_username;
    String id, username;
    SharedPreferences sharedpreferences;
    Toolbar toolbar;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_id2, txt_tanggal, txt_waktu,txt_lokasi, txt_kehadiran, txt_topik,txt_judul, txt_isi;
    String id2, tanggal, waktu, lokasi, kehadiran, topik, judul, isi;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    private static final String TAG = MainActivity.class.getSimpleName();

    private static String url_select 	 = Server.URL + "select.php";

    public static final String TAG_ID2       = "id";
    public static final String TAG_TANGGAL   = "tanggal";
    public static final String TAG_WAKTU     = "waktu";
    public static final String TAG_LOKASI    = "lokasi";
    public static final String TAG_KEHADIRAN = "kehadiran";
    public static final String TAG_TOPIK     = "topik";
    public static final String TAG_JUDUL   = "judul";
    public static final String TAG_ISI       = "isi";

    String tag_json_obj = "json_obj_req";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        getSupportActionBar().setTitle("Kepala Labor");
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_username = (TextView) findViewById(R.id.txt_username);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        txt_id.setText("ID : " + id);
        txt_username.setText("USERNAME : " + username);

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                editor.commit();

                Intent intent = new Intent(MainActivity1.this, Login.class);
                finish();
                startActivity(intent);
            }
        });

        // menghubungkan variablel pada layout dan pada java
        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list = (ListView) findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new Adapter( MainActivity1.this, itemList);
        list.setAdapter(adapter);

        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );

    }

        @Override
        public void onRefresh() {
            itemList.clear();
            adapter.notifyDataSetChanged();
            callVolley();
        }

        // untuk menampilkan semua data pada listview
        private void callVolley(){
            itemList.clear();
            adapter.notifyDataSetChanged();
            swipe.setRefreshing(true);

            // membuat request JSON
            JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, response.toString());

                    // Parsing json
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);

                            Data item = new Data();

                            item.setId2(obj.getString(TAG_ID2));
                            item.setTanggal(obj.getString(TAG_TANGGAL));
                            item.setWaktu(obj.getString(TAG_WAKTU));
                            item.setLokasi(obj.getString(TAG_LOKASI));
                            item.setKehadiran(obj.getString(TAG_KEHADIRAN));
                            item.setTopik(obj.getString(TAG_TOPIK));;
                            item.setJudul(obj.getString(TAG_JUDUL));
                            item.setIsi(obj.getString(TAG_ISI));

                            // menambah item ke array
                            itemList.add(item);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // notifikasi adanya perubahan data pada adapter
                    adapter.notifyDataSetChanged();

                    swipe.setRefreshing(false);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    swipe.setRefreshing(false);
                }
            });

            // menambah request ke request queue
            AppController.getInstance().addToRequestQueue(jArr);
        }









}
