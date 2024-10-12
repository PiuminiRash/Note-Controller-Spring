package lk.ijse.notecollectorspring.dto.impl;

import lk.ijse.notecollectorspring.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto implements UserStatus {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePic;
    private List<NoteDto> noteId;
}
