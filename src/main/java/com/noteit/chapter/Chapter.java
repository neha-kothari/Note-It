package com.noteit.chapter;

import com.noteit.notebook.Notebook;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterId;

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
}
