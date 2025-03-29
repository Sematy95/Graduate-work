package ru.skypro.homework.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", allocationSize = 1)
    private int pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private User author;

    private String text;
    private long createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", referencedColumnName = "pk", nullable = false)
    private Ad ad;

    public Comment() {
    }

    public Comment(User author, String text, long createdAt, Ad ad) {
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
        this.ad = ad;
    }

    public int getPk() {
        return pk;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Comment comment = (Comment) object;
        return createdAt == comment.createdAt && Objects.equals(author, comment.author) && Objects.equals(text, comment.text) && Objects.equals(ad, comment.ad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, author, text, createdAt, ad);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "pk=" + pk +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", ad=" + ad +
                '}';
    }
}
