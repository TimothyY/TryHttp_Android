package timothyyudi.tryhttp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDefaultHTTP,btnVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDefaultHTTP = (Button) findViewById(R.id.btnDefaultHTTP);
        btnDefaultHTTP.setOnClickListener(this);
        btnVolley = (Button) findViewById(R.id.btnVolley);
        btnVolley.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDefaultHTTP:
                Intent i = new Intent(this,DefaultHTTPActivity.class);
                startActivity(i);
                break;
            case R.id.btnVolley:
                Intent i2 = new Intent(this,VolleyActivity.class);
                startActivity(i2);
                break;

        }
    }
}
