package lk.ijse.notecollectorspring.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.notecollectorspring.Exception.DataPersistException;
import lk.ijse.notecollectorspring.Exception.NoteNotFoundException;
import lk.ijse.notecollectorspring.customStatusCode.SelectedUserAndNoteErrorStatus;
import lk.ijse.notecollectorspring.dao.NoteDao;
import lk.ijse.notecollectorspring.dto.NoteStatus;
import lk.ijse.notecollectorspring.dto.impl.NoteDto;
import lk.ijse.notecollectorspring.entity.impl.NoteEntity;
import lk.ijse.notecollectorspring.service.NoteService;
import lk.ijse.notecollectorspring.util.AppUtil;
import lk.ijse.notecollectorspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    private static List<NoteDto> noteDtoList = new ArrayList<>();

    @Autowired
    private NoteDao noteDao;
    @Autowired
    private Mapping notemapping;

    @Override
    public void saveNote(NoteDto noteDto) {
        noteDto.setNoteId(AppUtil.generateNoteId());
        //NoteEntity savedNote = notemapping.toNoteEntity(noteDto);
        NoteEntity saved = noteDao.save(notemapping.toNoteEntity(noteDto));
        if (saved == null){
            throw new DataPersistException("Note not saved");
        }
    }

    @Override
    public List<NoteDto> getAllNotes() {
        /*noteDtoList.add(new NoteDto(AppUtil.generateNoteId(), "Vehicle", "Story of vehicle manufacturing", "2024-09-15", "Level-01", "U001"));
        noteDtoList.add(new NoteDto(AppUtil.generateNoteId(), "Love", "Story of love", "2024-09-15", "Level-02", "U002"));
        return noteDtoList;*/
        return notemapping.noteDtoList(noteDao.findAll());
    }

    @Override
    public NoteStatus selectedNote(String noteId) {
        if (noteDao.existsById(noteId)){
            var selectedNote = noteDao.getReferenceById(noteId);
            return notemapping.toNoteDto(selectedNote);
        }
        return new SelectedUserAndNoteErrorStatus(2,"Selected note not found");
    }

    @Override
    public void deleteNote(String noteId) {
        Optional<NoteEntity> findNote = noteDao.findById(noteId);
        if (!findNote.isPresent()){
            throw new NoteNotFoundException("Note not found");
        }else {
            noteDao.deleteById(noteId);
        }
    }

    @Override
    public void updateNote(String noteId, NoteDto noteDto) {
        Optional<NoteEntity> findNote = noteDao.findById(noteId);
        if (!findNote.isPresent()){
            throw new NoteNotFoundException("Note not found");
        }else {
            findNote.get().setNoteTitle(noteDto.getNoteTitle());
            findNote.get().setNoteDesc(noteDto.getNoteDesc());
            findNote.get().setCreatedDate(noteDto.getCreatedDate());
            findNote.get().setPriorityLevel(noteDto.getPriorityLevel());
        }
    }

}
