package com.learning.webatm.service;

import com.learning.webatm.enums.Banknotes;
import com.learning.webatm.model.Notes;

import java.util.List;

public interface NotesService {

    Notes findNoteByType(Banknotes banknotes);
    List<Notes> findNotesByTypeLike(String type);
}
