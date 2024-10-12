package lk.ijse.notecollectorspring.service;

import lk.ijse.notecollectorspring.dto.UserStatus;
import lk.ijse.notecollectorspring.dto.impl.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserStatus getSelectedUser(String userId);
    void deleteUser(String userId);
    void updateUser(String userId, UserDto userDto);
}
