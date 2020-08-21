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
<<<<<<< HEAD

    public class MainActivity extends AppCompatActivity {
=======
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
>>>>>>> 7b084f13fe1469cc8a11839afb8d1e934b162787

        Button btn_logout;
        TextView txt_id, txt_username;
        String id, username;
        SharedPreferences sharedpreferences;
<<<<<<< HEAD
=======
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
        EditText txt_id2, txt_topik, txt_judul;
        String id2, topik, judul;
>>>>>>> 7b084f13fe1469cc8a11839afb8d1e934b162787

        public static final String TAG_ID = "id";
        public static final String TAG_USERNAME = "username";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            getSupportActionBar().setTitle("Admin");
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

<<<<<<< HEAD
        }




=======


        }


        @Override
        public void onRefresh() {

        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {

        }
>>>>>>> 7b084f13fe1469cc8a11839afb8d1e934b162787
    }
