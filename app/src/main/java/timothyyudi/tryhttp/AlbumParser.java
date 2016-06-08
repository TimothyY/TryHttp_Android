package timothyyudi.tryhttp;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by root on 6/8/2016.
 */
public class AlbumParser {

    public void parseJSONToAlbum(Context ctx, JSONObject source){
        ArrayList<Album> albums=new ArrayList<>();
        try {
            JSONArray arrAlbums = source.getJSONArray("results");
            for(int i=0;i<arrAlbums.length();i++){
                Album album = new Album();
                album.trackId = ((JSONObject)arrAlbums.get(i)).getString("trackId");
                album.trackName = ((JSONObject)arrAlbums.get(i)).getString("trackName");
                albums.add(album);
                Toast.makeText(ctx, album.trackName+" Added", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
