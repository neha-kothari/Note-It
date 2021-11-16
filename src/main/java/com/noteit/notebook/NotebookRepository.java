package com.noteit.notebook;

import com.noteit.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository  extends JpaRepository<Notebook, Long> {
}
