package com.noteit.bean;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="Notebook")
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notebookId;

    @Column(nullable = false) // C-In Cart and P-Processed & Merged
    private Character status;

    @Column
    private String notebookName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userId;

    @Column
    private String location;

    @Column
    private LocalDateTime creationDate;

    @ManyToMany
    @JoinTable(
            name = "notebook_chapters",
            joinColumns = @JoinColumn(name = "notebookId"),
            inverseJoinColumns = @JoinColumn(name = "chapterId"))
    private Set<BookChapter> chapters;

    public Notebook() {
    }

    public int getNotebookId() {
        return notebookId;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<BookChapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<BookChapter> chapters) {
        this.chapters = chapters;
    }
}
