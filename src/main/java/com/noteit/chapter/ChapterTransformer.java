package com.noteit.chapter;

import com.noteit.dto.ChapterDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ChapterTransformer {

    @Resource
    private ChapterRepository chapterRepository;

    public ChapterDTO toDto(Chapter chapter) {
        ChapterDTO chapterDTO = new ChapterDTO();

        if (chapter == null) return chapterDTO;

        chapterDTO.setChapterId(chapter.getChapterId());
        chapterDTO.setChapterNumber(chapter.getChapterNumber());
        chapterDTO.setChapterName(chapter.getChapterName());
        chapterDTO.setDescription(chapter.getDescription());
        chapterDTO.setStartPage(chapter.getStartPage());
        chapterDTO.setEndPage(chapterDTO.getEndPage());

        return chapterDTO;
    }

    public Chapter toEntity(Chapter chapter, ChapterDTO request) {

        chapter.setChapterName(request.getChapterName());
        chapter.setChapterNumber(request.getChapterNumber());
        chapter.setDescription(request.getDescription());
        chapter.setStartPage(request.getStartPage());
        chapter.setEndPage(request.getEndPage());
        return chapter;
    }
}
