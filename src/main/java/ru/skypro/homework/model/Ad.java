package ru.skypro.homework.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_seq")
    @SequenceGenerator(name = "ad_seq", allocationSize = 1)
    private int pk;

    private String title;
    private String description;
    private String image;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "ad")
    private List<Comment> comments = new ArrayList<>();

    public Ad() {
    }

    public Ad(String title, String description, String image, int price, User author) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.author = author;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Ad ad = (Ad) object;
        return pk == ad.pk && price == ad.price && Objects.equals(title, ad.title) && Objects.equals(description, ad.description) && Objects.equals(image, ad.image) && Objects.equals(author, ad.author) && Objects.equals(comments, ad.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, title, description, image, price, author, comments);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "pk=" + pk +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", author=" + author +
                ", comments=" + comments +
                '}';
    }
}
