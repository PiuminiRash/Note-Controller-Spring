package lk.ijse.notecollectorspring.dto.impl;


import lk.ijse.notecollectorspring.dto.NoteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto implements NoteStatus {
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String createdDate;
    private String priorityLevel;
    private String userId;
}
