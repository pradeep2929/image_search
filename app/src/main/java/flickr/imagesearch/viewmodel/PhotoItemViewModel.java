package flickr.imagesearch.viewmodel;

import android.content.Context;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import flickr.imagesearch.data.DownloadImageTask;
import flickr.imagesearch.data.utils.BitmapCache;
import flickr.imagesearch.data.utils.URLBuilder;
import flickr.imagesearch.model.Photo;


public class PhotoItemViewModel extends BaseObservable
{
    private static final String TAG = PhotoItemViewModel.class.getSimpleName();

    private Photo photo;
    private Context context;
    private URLBuilder url;


    /**
     * Constructor to initialize / instantiate all variable / classes
     * @param context application context
     * @param photo photo instance
     */
    public PhotoItemViewModel(Context context, Photo photo)
    {
        this.photo = photo;
        this.context = context;
        this.url = new URLBuilder();
    }

    /**
     * this method used to return photo url
     * @return photo url
     */
    public String getThumbnail()
    {
        return url.toPhotoUrl(photo);
    }

    /**
     * this method loads images on gridview image
     * @param imageView imageview instance
     * @param url url to load
     */
    @BindingAdapter("imageUrl")
    public static void setImageUrl(final ImageView imageView, String url)
    {
        try
        {
            Bitmap bitmap = BitmapCache.getInstance().getBitmap(url);

            if(bitmap != null)
            {
                Log.i(TAG, "Bitmap found on cache");

                imageView.setImageBitmap(bitmap);
                return;
            }

            DownloadImageTask task = new DownloadImageTask();

            task.setHttpListener(new DownloadImageTask.HttpCallback() {

                @Override
                public void onPostExecute(final Bitmap bitmap)
                {
                    imageView.setImageBitmap(bitmap);
                }

                @Override
                public void onPreExecute()
                {

                }
            });

            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * set photo and notify
     * @param photo photo instance
     */
    public void setPhoto(Photo photo)
    {
        this.photo = photo;
        notifyChange();
    }
}