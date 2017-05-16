package timothyyudi.tryhttp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DefaultHTTPActivity extends AppCompatActivity {

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_http);

        ctx = this;
        DemoTask task = new DemoTask();
        task.execute(); //start requesting data from internet
    }

    /**read byte data pulled from internet and convert it to string*/
    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                buffer.write(i);
                i = is.read();
            }
            return buffer.toString();
        } catch (IOException e) {
            return "";
        }
    }

    /**A background task which request data from internet*/
    private class DemoTask extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... urls) {
            String result="";
            try {
                URL url = new URL("https://itunes.apple.com/search?term=hoobastank");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = readStream(in);
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onProgressUpdate(Integer... progress) {}

        protected void onPostExecute(String result) {
            AlbumParser albumParser = new AlbumParser(); //preparing an object of AlbumParser class
            try {
                albumParser.parseJSONToAlbum(ctx, new JSONObject(result)); //convert the string to JSON object and then read it using our own parser
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
