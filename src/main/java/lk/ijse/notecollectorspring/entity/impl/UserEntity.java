package lk.ijse.notecollectorspring.entity.impl;


import jakarta.persistence.*;
import lk.ijse.notecollectorspring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity implements SuperEntity {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;
    @OneToMany(mappedBy = "user")
    private List<NoteEntity> noteId;
}
