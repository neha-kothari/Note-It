package com.noteit.notebook;

import com.noteit.chapter.Chapter;
import com.noteit.chapter.ChapterRepository;
import com.noteit.dto.NotebookDTO;
import com.noteit.dto.NotesOutputDTO;
import com.noteit.user.User;
import com.noteit.user.UserRepository;
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
    private UserRepository userRepository;

    @Resource
    private ChapterRepository chapterRepository;

    @Resource
    private NotebookTransformer notebookTransformer;

    @Override
    @Transactional
    public void saveNotes(NotebookDTO notebookDTO, Long user_id) {

        Notebook notebook = notebookRepository.findNotebookIdForUser(user_id);
        if (null == notebook) {
            notebook = new Notebook();
            notebook.setStatus('C');
            notebook.setUserId(userRepository.findByUserId(user_id));
        }

        notebook = notebookTransformer.toEntity(notebook, notebookDTO);
        notebookRepository.save(notebook);
    }

    @Override
    @Transactional
    public NotesOutputDTO getNotes(Long user_id) {
        Notebook notebook = notebookRepository.findNotebookIdForUser(user_id);
        if (null == notebook) {
            return null;
        }
        return notebookTransformer.toNotesOutputDTO(notebook);
    }

    @Override
    @Transactional
    public NotesOutputDTO deleteChapter(Long user_id, Long chapter_id) {
        Notebook notebook = notebookRepository.findNotebookIdForUser(user_id);
        if (null == notebook) {
            return null;
        }
        if (notebook.getChapters() != null) {
            Chapter chapter = chapterRepository.findByChapterId(chapter_id);
            if (notebook.getChapters().contains(chapter)) {
                notebook.getChapters().remove(chapter);
            }
            notebookRepository.save(notebook);
        }

        return notebookTransformer.toNotesOutputDTO(notebook);
    }
}
