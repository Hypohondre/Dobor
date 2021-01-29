package models;

import java.util.Objects;

public class Post {
    private Long id;
    private String text;
    private String img;
    private Long creator_id;
    private Long category_id;

    public Post(String text,String img, Long creator_id, Long category_id) {
            this.text = text;
            this.img = img;
            this.creator_id = creator_id;
            this.category_id = category_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return text.equals(post.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getImg() {
        return img;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
