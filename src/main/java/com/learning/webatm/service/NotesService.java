package com.learning.webatm.service;

import com.learning.webatm.enums.Banknotes;
import com.learning.webatm.model.Notes;

public interface NotesService {

    Notes findNoteByType(Banknotes banknotes);
}
