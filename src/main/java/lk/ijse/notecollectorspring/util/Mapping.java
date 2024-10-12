package lk.ijse.notecollectorspring.util;

import lk.ijse.notecollectorspring.dto.impl.NoteDto;
import lk.ijse.notecollectorspring.dto.impl.UserDto;
import lk.ijse.notecollectorspring.entity.impl.NoteEntity;
import lk.ijse.notecollectorspring.entity.impl.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    // for user mapping
    public UserEntity toUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
    public UserDto toUserDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }
    public List<UserDto> userDtoList(List<UserEntity> userEntityList) {
        return modelMapper.map(userEntityList, new TypeToken<List<UserDto>>() {}.getType()  );
    }

    // for note mapping
    public NoteEntity toNoteEntity(NoteDto noteDto) {
        return modelMapper.map(noteDto, NoteEntity.class);
    }
    public NoteDto toNoteDto(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity, NoteDto.class);
    }
    public List<NoteDto> noteDtoList(List<NoteEntity> noteEntityList) {
        return modelMapper.map(noteEntityList, new TypeToken<List<NoteDto>>() {}.getType()  );
    }
}
