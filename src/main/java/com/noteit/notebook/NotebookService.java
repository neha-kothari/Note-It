package com.noteit.notebook;

import com.noteit.dto.NotebookDTO;
import com.noteit.user.User;

public interface NotebookService {

    void saveNotes(NotebookDTO notebookDTO, User user);
}
