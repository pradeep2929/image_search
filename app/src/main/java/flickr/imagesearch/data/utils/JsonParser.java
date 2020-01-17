package flickr.imagesearch.data.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import flickr.imagesearch.data.FlickrApiResponse;
import flickr.imagesearch.model.Photo;

public class JsonParser
{

    /**
     * this method converts the json string to Flickr Response Object
     * @param data json string
     * @return flickr object
     */
    public static FlickrApiResponse deserializeFlickrApiResponse(String data)
    {
        FlickrApiResponse flickrApiResponse = null;

        try
        {
            List<Photo> photoList = new ArrayList<>();

            JSONObject json = new JSONObject(data);
            json = json.getJSONObject("photos");

            int page        = json.getInt("page");
            int pages       = json.getInt("pages");
            int perpage     = json.getInt("perpage");
            int total       = json.getInt("total");

            JSONArray array = json.getJSONArray("photo");

            for(int i=0; i<array.length(); i++)
            {
                JSONObject photoObj = array.getJSONObject(i);

                String id     = photoObj.getString("id");
                String owner  = photoObj.getString("owner");
                String secret = photoObj.getString("secret");
                String server = photoObj.getString("server");
                int farm      = photoObj.getInt("farm");
                String title  = photoObj.getString("title");
                int ispublic  = photoObj.getInt("ispublic");
                int isfriend  = photoObj.getInt("isfriend");
                int isfamily  = photoObj.getInt("isfamily");

                photoList.add(new Photo(id, owner, secret, server, farm, title, ispublic));
            }

            flickrApiResponse = new FlickrApiResponse(page, pages, 20, total, photoList);
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return flickrApiResponse;
    }
}