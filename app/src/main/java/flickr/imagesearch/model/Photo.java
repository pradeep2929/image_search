package flickr.imagesearch.model;

public class Photo
{
    private String id;
    private String owner;
    private String secret;
    private String server;
    private int farm;
    private String title;
    private int ispublic;



    public Photo()
    {

    }

    public Photo(String id, String owner, String secret, String server, int farm, String title, int ispublic)
    {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
    }


    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getOwner()
    {
        return this.owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getSecret()
    {
        return this.secret;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public String getServer()
    {
        return this.server;
    }

    public void setServer(String server)
    {
        this.server = server;
    }

    public int getFarm()
    {
        return this.farm;
    }

    public void setFarm(int farm)
    {
        this.farm = farm;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getPublic()
    {
        return this.ispublic;
    }

    public void setPublic(int ispublic)
    {
        this.ispublic = ispublic;
    }

   }