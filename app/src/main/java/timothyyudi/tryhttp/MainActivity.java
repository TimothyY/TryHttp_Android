package timothyyudi.tryhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DemoTask task = new DemoTask();
        task.execute();

    }

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

    private class DemoTask extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... urls) {
            URL url = null;
            String result="";
            try {
                url = new URL("https://www.android.com/");
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
            Log.v("httpresult",result);
        }
    }
}
