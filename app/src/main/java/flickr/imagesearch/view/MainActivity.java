package flickr.imagesearch.view;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import flickr.imagesearch.R;
import flickr.imagesearch.data.FlickrApiResponse;
import flickr.imagesearch.data.utils.URLBuilder;
import flickr.imagesearch.databinding.ActivityMainBinding;
import flickr.imagesearch.model.Photo;
import flickr.imagesearch.view.adapter.PhotoAdapter;
import flickr.imagesearch.viewmodel.PhotoViewModel;


public class MainActivity extends AppCompatActivity implements Observer
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.bind();
        this.setAdapter(this.binding.gridview);
    }

    /**
     * initialize activity binding auto generated class
     * initialise PhotoViewModel instance
     * call setup observer method
     * bind viewmodel instance
     * URlBuilder instance
     * FlickrApiResponse instance
     */
    private void bind()
    {
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        PhotoViewModel photoViewModel = new PhotoViewModel(this, new ArrayList<Photo>(), new URLBuilder(), new FlickrApiResponse());

        this.setupObserver(photoViewModel);
        this.binding.setPhotoViewModel(photoViewModel);
    }


    /**
     * set gridview adapter
     * @param gridView gridview instance
     */
    private void setAdapter(GridView gridView)
    {
        gridView.setAdapter(new PhotoAdapter(this, new ArrayList<Photo>()));
    }


    /**
     * add observer
     * @param observable
     */
    public void setupObserver(Observable observable)
    {
        observable.addObserver(this);
    }

    /**
     * Overridden method of observer class
     * Calls and set photolist to adapter
     * @param observable
     * @param o
     */
    @Override
    public void update(Observable observable, Object o)
    {
        if (observable instanceof PhotoViewModel)
        {
            PhotoAdapter adapter = (PhotoAdapter) binding.gridview.getAdapter();
            PhotoViewModel photoViewModel = (PhotoViewModel) observable;
            adapter.setPhotoList(photoViewModel.getPhotoList());
        }
    }


    /**
     * Overridden method for hide keyboard on UI touch
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        try
        {
            if (getCurrentFocus() != null)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                if(imm != null)
                {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return super.dispatchTouchEvent(ev);
    }
}