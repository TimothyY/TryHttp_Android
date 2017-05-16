package timothyyudi.tryhttp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    RequestQueue vRequestQueue;
    Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        mCtx = this;
        vRequestQueue = Volley.newRequestQueue(this);
        vRequestQueue.add(new JsonObjectRequest(Request.Method.GET, "https://itunes.apple.com/search?term=hoobastank", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                AlbumParser albumParser = new AlbumParser(); //preparing an object of AlbumParser class
                albumParser.parseJSONToAlbum(mCtx, response); //convert the string to JSON object and then read it using our own parser
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));

    }
}
