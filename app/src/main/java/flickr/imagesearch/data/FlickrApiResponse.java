package flickr.imagesearch.data;

import java.util.List;

import flickr.imagesearch.model.Photo;

public class FlickrApiResponse
{
    private int page;
    private int pages;
    private int perpage;
    private int total;
    private List<Photo> photo;

    /**
     * empty constructor
     */
    public FlickrApiResponse()
    {

    }

    /**
     *
     * @param page current page
     * @param pages number of page
     * @param perpage result per page
     * @param total total number of records
     * @param photo list of photos
     */
    public FlickrApiResponse(int page, int pages, int perpage, int total, List<Photo> photo)
    {
        this.page = page;
        this.pages = pages;
        this.perpage = perpage;
        this.total = total;
        this.photo = photo;
    }

    public int getPage()
    {
        return this.page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getPages()
    {
        return this.pages;
    }

    public void setPages(int pages)
    {
        this.pages = pages;
    }

    public int getPerpage()
    {
        return this.perpage;
    }

    public void setPerpage(int perpage)
    {
        this.perpage = perpage;
    }

    public int getTotal()
    {
        return this.total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public List<Photo> getPhoto()
    {
        return this.photo;
    }

    public void setPhoto(List<Photo> photo)
    {
        this.photo = photo;
    }
}