package lk.ijse.notecollectorspring.controller;

import lk.ijse.notecollectorspring.Exception.DataPersistException;
import lk.ijse.notecollectorspring.Exception.NoteNotFoundException;
import lk.ijse.notecollectorspring.customStatusCode.SelectedUserAndNoteErrorStatus;
import lk.ijse.notecollectorspring.dto.NoteStatus;
import lk.ijse.notecollectorspring.dto.impl.NoteDto;
import lk.ijse.notecollectorspring.service.NoteService;
import lk.ijse.notecollectorspring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNote(@RequestBody NoteDto noteDto){
        try {
            noteService.saveNote(noteDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteStatus getSelectedNote(@PathVariable("noteId") String noteId){
        if (!RegexProcess.noteIdMatcher(noteId)){
            return new SelectedUserAndNoteErrorStatus(1,"Note id is not valid");
        }
        return noteService.selectedNote(noteId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDto> getAllNotes(){
        return noteService.getAllNotes();
    }
    @DeleteMapping(value = "{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable("noteId") String noteId){
        try {
            if (!RegexProcess.noteIdMatcher(noteId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            noteService.deleteNote(noteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "{noteId}")
    public ResponseEntity<Void> updateNote(@PathVariable("noteId") String noteId, @RequestBody NoteDto updatedNoteDto){
        try {
            if (!RegexProcess.noteIdMatcher(noteId) || updatedNoteDto == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            noteService.updateNote(noteId, updatedNoteDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
