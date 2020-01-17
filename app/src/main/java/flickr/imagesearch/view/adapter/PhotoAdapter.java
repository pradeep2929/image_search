package flickr.imagesearch.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import flickr.imagesearch.R;
import flickr.imagesearch.databinding.ItemPhotoBinding;
import flickr.imagesearch.model.Photo;
import flickr.imagesearch.viewmodel.PhotoItemViewModel;


public class PhotoAdapter extends BaseAdapter
{
    private static final String TAG = PhotoAdapter.class.getSimpleName();

    private Context mContext;
    private List<Photo> photoList;
    private ItemPhotoBinding binding;

    /**
     * Constructor to initialize / instantiate all variable / classes
     * @param context application context
     * @param photoList photo list instance
     */
    public PhotoAdapter(Context context, List<Photo> photoList)
    {
        mContext = context;
        this.photoList = photoList;
    }

    /**
     * Overridden method of BaseAdapter
     * @return return photolist size
     */
    @Override
    public int getCount()
    {
        return photoList.size();
    }

    /**
     * Overridden method of BaseAdapter
     * @return photo on specified position
     */
    @Override
    public Photo getItem(int position)
    {
        return this.photoList.get(position);
    }

    /**
     * Overridden method of BaseAdapter
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    /**
     * clear photo list and add all the items
     * notify adapter changes
     */
    public void setPhotoList(List<Photo> photoList)
    {
        this.photoList.clear();
        this.photoList.addAll(photoList);
        notifyDataSetChanged();
    }

    public List<Photo> getPhotoList()
    {
        return this.photoList;
    }

    /**
     * overridden method of BaseAdapter
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_photo, null);
            binding = DataBindingUtil.bind(convertView);
            convertView.setTag(binding);
        }

        else
        {
            binding = (ItemPhotoBinding) convertView.getTag();
        }

        if (binding.getPhotoItemVM() == null)
        {
            binding.setPhotoItemVM(new PhotoItemViewModel(convertView.getContext(), photoList.get(position)));
        }

        else
        {
            binding.getPhotoItemVM().setPhoto(photoList.get(position));
        }

        return binding.getRoot();
    }
}