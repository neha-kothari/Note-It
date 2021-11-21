package com.noteit.notebook;

import com.noteit.chapter.Chapter;
import com.noteit.chapter.ChapterRepository;
import com.noteit.dto.NotebookDTO;
import com.noteit.user.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class NotebookServiceImpl implements NotebookService{

    @Resource
    private NotebookRepository notebookRepository;

    @Resource
    private ChapterRepository chapterRepository;

    @Override
    @Transactional
    public void saveNotes(NotebookDTO notebookDTO, User user) {

        Notebook notebook = notebookRepository.findNotebookIdForUser(user.getUserId());
        if (null == notebook) {
            notebook = new Notebook();
            notebook.setStatus('C');
            notebook.setUserId(user);
        }
        HashSet<Chapter> chapters = new HashSet<>();
        for (Long chapterId : notebookDTO.getSelected_chapters()) {
            chapters.add(chapterRepository.findByChapterId(chapterId));
        }
        notebook.getChapters().addAll(chapters);
        notebookRepository.save(notebook);
    }
}
