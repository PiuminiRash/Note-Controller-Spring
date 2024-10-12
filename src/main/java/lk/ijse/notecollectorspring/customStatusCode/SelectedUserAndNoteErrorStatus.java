package lk.ijse.notecollectorspring.customStatusCode;

import lk.ijse.notecollectorspring.dto.NoteStatus;
import lk.ijse.notecollectorspring.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedUserAndNoteErrorStatus implements UserStatus, NoteStatus {
    private int statusCode;
    private String status;

}
