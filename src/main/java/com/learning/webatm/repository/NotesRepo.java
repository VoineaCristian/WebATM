package com.learning.webatm.repository;

import com.learning.webatm.enums.Banknotes;
import com.learning.webatm.model.Notes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepo extends CrudRepository<Notes,Long> {

    Notes findNoteByType(Banknotes banknotes);
}
