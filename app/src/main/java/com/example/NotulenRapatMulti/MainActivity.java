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

    public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

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
        private static String url_insert 	 = Server.URL + "insert.php";
        private static String url_edit 	     = Server.URL + "edit.php";
        private static String url_update 	 = Server.URL + "update.php";
        private static String url_delete 	 = Server.URL + "delete.php";

        public static final String TAG_ID2       = "id";
        public static final String TAG_TANGGAL   = "tanggal";
        public static final String TAG_WAKTU     = "waktu";
        public static final String TAG_LOKASI    = "lokasi";
        public static final String TAG_KEHADIRAN = "kehadiran";
        public static final String TAG_TOPIK     = "topik";
        public static final String TAG_JUDUL   = "judul";
        public static final String TAG_ISI       = "isi";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";

        String tag_json_obj = "json_obj_req";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            getSupportActionBar().setTitle("Sekretaris");
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

                    Intent intent = new Intent(MainActivity.this, Login.class);
                    finish();
                    startActivity(intent);
                }
            });

            // menghubungkan variablel pada layout dan pada java
            fab     = (FloatingActionButton) findViewById(R.id.fab_add);
            swipe   = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
            list    = (ListView) findViewById(R.id.list);

            // untuk mengisi data dari JSON ke dalam adapter
            adapter = new Adapter(MainActivity.this, itemList);
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

            // fungsi floating action button memanggil form
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogForm("0","null","null","null","null",
                            "null","null", "null","SIMPAN" );
                }
            });

            // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                               final int position, long id) {
                    // TODO Auto-generated method stub
                    final String idx = itemList.get(position).getId2();

                    final CharSequence[] dialogitem = {"Edit", "Delete"};
                    dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setCancelable(true);
                    dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            switch (which) {
                                case 0:
                                    edit(idx);
                                    break;
                                case 1:
                                    delete(idx);
                                    break;
                            }
                        }
                    }).show();
                    return false;
                }
            });

        }

        @Override
        public void onRefresh() {
            itemList.clear();
            adapter.notifyDataSetChanged();
            callVolley();
        }

        // untuk mengosongi edittext pada form
        private void kosong(){
            txt_id2.setText(0);
            txt_tanggal.setText("");
            txt_waktu.setText("");
            txt_lokasi.setText("");
            txt_kehadiran.setText("");
            txt_topik.setText("");
            txt_judul.setText("");
            txt_isi.setText("");
        }

        // untuk menampilkan dialog form
        private void DialogForm(String idx, String tanggalx, String waktux,  String lokasix, String kehadiranx, String topikx, String judulx, String isix, String button) {
            dialog = new AlertDialog.Builder(MainActivity.this);
            inflater = getLayoutInflater();
            dialogView = inflater.inflate(R.layout.form_notulen, null);
            dialog.setView(dialogView);
            dialog.setCancelable(true);
            dialog.setIcon(R.mipmap.ic_launcher);
            dialog.setTitle("Form Notulen");

            txt_id      = (EditText) dialogView.findViewById(R.id.txt_id2);
            txt_tanggal = (EditText) dialogView.findViewById(R.id.txt_tanggal);
            txt_waktu  = (EditText) dialogView.findViewById(R.id.txt_waktu);
            txt_lokasi = (EditText) dialogView.findViewById(R.id.txt_lokasi);
            txt_kehadiran = (EditText) dialogView.findViewById(R.id.txt_kehadiran);
            txt_topik = (EditText) dialogView.findViewById(R.id.txt_topik);
            txt_judul = (EditText) dialogView.findViewById(R.id.txt_judul);
            txt_isi = (EditText) dialogView.findViewById(R.id.txt_isi);

            if (!idx.isEmpty()){
                txt_id.setText(idx);
                txt_tanggal.setText(tanggalx);
                txt_waktu.setText(waktux);
                txt_lokasi.setText(lokasix);
                txt_kehadiran.setText(kehadiranx);
                txt_topik.setText(topikx);
                txt_judul.setText(judulx);
                txt_isi.setText(isix);
            } else {
                kosong();
            }

            dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    id2      = txt_id2.getText().toString();
                    tanggal    = txt_tanggal.getText().toString();
                    waktu  = txt_waktu.getText().toString();
                    lokasi    = txt_lokasi.getText().toString();
                    kehadiran  = txt_kehadiran.getText().toString();
                    topik  = txt_topik.getText().toString();
                    judul    = txt_judul.getText().toString();
                    isi  = txt_isi.getText().toString();

                    simpan_update();
                    dialog.dismiss();
                }
            });

            dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    kosong();
                }
            });

            dialog.show();
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
                            item.setTopik(obj.getString(TAG_TANGGAL));
                            item.setTopik(obj.getString(TAG_WAKTU));
                            item.setTopik(obj.getString(TAG_LOKASI));
                            item.setTopik(obj.getString(TAG_KEHADIRAN));
                            item.setTopik(obj.getString(TAG_TOPIK));;
                            item.setJudul(obj.getString(TAG_JUDUL));
                            item.setTopik(obj.getString(TAG_TOPIK));

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

        // fungsi untuk menyimpan atau update
        private void simpan_update() {
            String url;
            // jika id kosong maka simpan, jika id ada nilainya maka update
            if (id.isEmpty()){
                url = url_insert;
            } else {
                url = url_update;
            }

            StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response: " + response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCESS);

                        // Cek error node pada json
                        if (success == 1) {
                            Log.d("Add/update", jObj.toString());

                            callVolley();
                            kosong();

                            Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                            adapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error: " + error.getMessage());
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters ke post url
                    Map<String, String> params = new HashMap<String, String>();
                    // jika id kosong maka simpan, jika id ada nilainya maka update
                    if (id.isEmpty()){
                        params.put("tanggal", tanggal);
                        params.put("waktu", waktu);
                        params.put("lokasi", lokasi);
                        params.put("kehadiran", kehadiran);
                        params.put("topik", topik);
                        params.put("judul", judul);
                        params.put("isi", isi);

                    } else {
                        params.put("id2", id2);
                        params.put("tanggal", tanggal);
                        params.put("waktu", waktu);
                        params.put("lokasi", lokasi);
                        params.put("kehadiran", kehadiran);
                        params.put("topik", topik);
                        params.put("judul", judul);
                        params.put("isi", isi);
                    }

                    return params;
                }

            };

            AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        }

        // fungsi untuk get edit data
        private void edit(final String idx){
            StringRequest strReq = new StringRequest(Request.Method.POST, url_edit, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response: " + response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCESS);

                        // Cek error node pada json
                        if (success == 1) {
                            Log.d("get edit data", jObj.toString());
                            String idx      = jObj.getString(TAG_ID2);
                            String tanggalx    = jObj.getString(TAG_TANGGAL);
                            String waktux  = jObj.getString(TAG_WAKTU);
                            String lokasix  = jObj.getString(TAG_LOKASI);
                            String kehadiranx  = jObj.getString(TAG_KEHADIRAN);
                            String topikx  = jObj.getString(TAG_TOPIK);
                            String judulx  = jObj.getString(TAG_JUDUL);
                            String isix  = jObj.getString(TAG_ISI);

                            DialogForm(idx, tanggalx, waktux, lokasix, kehadiranx, topikx, judulx, isix, "UPDATE");

                            adapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error: " + error.getMessage());
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters ke post url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", idx);

                    return params;
                }

            };

            AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        }

        // fungsi untuk menghapus
        private void delete(final String idx){
            StringRequest strReq = new StringRequest(Request.Method.POST, url_delete, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response: " + response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCESS);

                        // Cek error node pada json
                        if (success == 1) {
                            Log.d("delete", jObj.toString());

                            callVolley();

                            Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                            adapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error: " + error.getMessage());
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters ke post url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", idx);

                    return params;
                }

            };

            AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        }

    }
