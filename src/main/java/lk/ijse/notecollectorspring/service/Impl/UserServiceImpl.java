package lk.ijse.notecollectorspring.service.Impl;

import lk.ijse.notecollectorspring.Exception.DataPersistException;
import lk.ijse.notecollectorspring.Exception.UserNotFoundException;
import lk.ijse.notecollectorspring.customStatusCode.SelectedUserAndNoteErrorStatus;
import lk.ijse.notecollectorspring.dao.UserDao;
import lk.ijse.notecollectorspring.dto.UserStatus;
import lk.ijse.notecollectorspring.dto.impl.UserDto;
import lk.ijse.notecollectorspring.entity.impl.UserEntity;
import lk.ijse.notecollectorspring.service.UserService;
import lk.ijse.notecollectorspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDto userDto) {
        UserEntity savedUser = userDao.save(mapping.toUserEntity(userDto));
        if (savedUser == null) {
            throw new DataPersistException("Save user failed");
        }
        // mapping.toUserDto(userDao.save(mapping.toUserEntity(userDto)));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> allUsers = userDao.findAll();
        return mapping.userDtoList(allUsers);
    }

    @Override
    public UserStatus getSelectedUser(String userId) {
        /*UserEntity selectedUser = userDao.getReferenceById(userId);
        if (selectedUser == null) {
            return new SelectedUserErrorStatus(2,"user");
        }
        return mapping.toUserDto(selectedUser);*/
        if (userDao.existsById(userId)) {
            UserEntity selectedUser = userDao.getReferenceById(userId);
            return mapping.toUserDto(selectedUser);
        }else {
            return new SelectedUserAndNoteErrorStatus(2,"user with id "+ userId + "not found");

        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> existsUser = userDao.findById(userId);
        if(existsUser.isPresent()) {
            throw new UserNotFoundException("user with id "+ userId + " not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public void updateUser(String userId, UserDto userDto) {
        Optional<UserEntity> tempUser = userDao.findById(userId);
        if (tempUser.isPresent()) {
            tempUser.get().setFirstName(userDto.getFirstName());
            tempUser.get().setLastName(userDto.getLastName());
            tempUser.get().setEmail(userDto.getEmail());
            tempUser.get().setPassword(userDto.getPassword());
            tempUser.get().setProfilePic(userDto.getProfilePic());
        }
    }
}
