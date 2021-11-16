package com.noteit.bean;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="BookChapter")
public class BookChapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chapterId;

    @Column(nullable = false)
    private Integer bookId;

    @Column(nullable = false)
    private Integer chapterNumber;

    @Column(nullable = false)
    private String chapterName;

    @Column(nullable = false)
    private Integer startPage;

    @Column(nullable = false)
    private Integer endPage;

    private String description;

    @ManyToMany(mappedBy = "chapters")
    private Set<Notebook> notebookSet;

    public BookChapter() {

    }

    public BookChapter(Integer bookId, Integer chapterNumber, String chapterName, Integer startPage, Integer endPage, String description) {
        this.bookId = bookId;
        this.chapterNumber = chapterNumber;
        this.chapterName = chapterName;
        this.startPage = startPage;
        this.endPage = endPage;
        this.description = description;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
