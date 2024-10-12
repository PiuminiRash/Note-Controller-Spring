package lk.ijse.notecollectorspring.dao;

import lk.ijse.notecollectorspring.entity.impl.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface NoteDao extends JpaRepository<NoteEntity,String> {
}
