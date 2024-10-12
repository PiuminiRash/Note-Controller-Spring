package lk.ijse.notecollectorspring.service;

import lk.ijse.notecollectorspring.dto.NoteStatus;
import lk.ijse.notecollectorspring.dto.impl.NoteDto;

import java.util.List;

public interface NoteService {
    void saveNote(NoteDto noteDto);
    List<NoteDto> getAllNotes();
    NoteStatus selectedNote(String noteId);
    void deleteNote(String noteId);
    void updateNote(String noteId, NoteDto noteDto);
}
