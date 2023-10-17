package SmarotModels;


// модель это описание джавы объекта из джейсона

public class Pet {
    private Status status;
    private long id;
    private String name;
    private String[] photoUrls;
    private Category category;
    private Tag[] tags;

    public Pet(Status status, String name, String[] photoUrls, Category category, Tag[] tags) {
        this.status = status;
        this.name = name;
        this.photoUrls = photoUrls;
        this.category = category;
        this.tags = tags;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public Category getCategory() {
        return category;
    }

    public Tag[] getTags() {
        return tags;
    }
}