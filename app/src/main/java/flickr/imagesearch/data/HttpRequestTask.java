package flickr.imagesearch.data;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import flickr.imagesearch.data.utils.JsonParser;


public class HttpRequestTask extends AsyncTask<String, Integer, FlickrApiResponse>
{
    private static final String TAG = HttpRequestTask.class.getSimpleName();

    private HttpCallback callbackListener;
    private int statusCode;

    /**
     * execute before http request
     */
    @Override
    protected void onPreExecute()
    {
        callbackListener.onPreExecute();
    }

    /**
     * this method calls when call execute method
     * @param params variable args parameter
     * @return serialized response
     */
    @Override
    protected FlickrApiResponse doInBackground(String... params)
    {
        BufferedReader reader = null;

        try
        {
            URL url = new URL(params[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // setting the  Request Method Type
            connection.setRequestMethod("GET");

            // adding the headers for request
            connection.setRequestProperty("Content-Type", "application/json");

            connection.connect();

            try
            {

                this.statusCode = connection.getResponseCode();

                InputStream inputStream = connection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null)
                {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = reader.readLine()) != null)
                {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0)
                {
                    return null;
                }

                return JsonParser.deserializeFlickrApiResponse(buffer.toString());
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }

            finally
            {
                if (reader != null)
                {
                    try
                    {
                        reader.close();
                    }

                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * execute after http response
     * @param response serialized response
     */
    @Override
    protected void onPostExecute(FlickrApiResponse response)
    {
        callbackListener.onPostExecute(response, statusCode);
    }


    /**
     * HttpCallback Interface
     */
    public interface HttpCallback
    {
        void onPostExecute(FlickrApiResponse response, int statusCode);
        void onPreExecute();
    }

    /**
     * method to set http callback interface
     * @param callbackListener HttpCallback instance
     */
    public void setHttpListener(final HttpCallback callbackListener)
    {
        this.callbackListener = callbackListener;
    }
}