package com.learning.webatm.repository;

import com.learning.webatm.model.Drawer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrawerRepo extends CrudRepository<Drawer, Long> {

    Drawer getDrawerById(Long id);

}
