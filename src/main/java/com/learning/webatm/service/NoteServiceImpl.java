package com.learning.webatm.service;

import com.learning.webatm.enums.Banknotes;
import com.learning.webatm.model.Notes;
import com.learning.webatm.repository.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NotesService{

    @Autowired
    NotesRepo notesRepo;

    @Override
    public Notes findNoteByType(Banknotes banknotes) {
        return notesRepo.findNoteByType(banknotes);
    }

    @Override
    public List<Notes> findNotesByTypeLike(String type) {
        return notesRepo.findNotesByTypeLike(type);
    }

}
