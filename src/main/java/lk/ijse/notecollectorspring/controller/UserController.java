package lk.ijse.notecollectorspring.controller;

import lk.ijse.notecollectorspring.Exception.DataPersistException;
import lk.ijse.notecollectorspring.Exception.UserNotFoundException;
import lk.ijse.notecollectorspring.customStatusCode.SelectedUserAndNoteErrorStatus;
import lk.ijse.notecollectorspring.dto.UserStatus;
import lk.ijse.notecollectorspring.dto.impl.UserDto;
import lk.ijse.notecollectorspring.service.UserService;
import lk.ijse.notecollectorspring.util.AppUtil;
import lk.ijse.notecollectorspring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic

    ) {
        // Done : profilePic ----> base64(convert)
        System.out.println("RAW pro pic " + profilePic);

        String base64proPic = "";

        try {
            byte[] byteProPic = profilePic.getBytes();
            base64proPic = AppUtil.generateProfilePicToBase64(byteProPic);

            // Done : Set Generated Id
            String userId = AppUtil.userId();

            // Done : Build Object
            var builduserDto = new UserDto();
            builduserDto.setUserId(userId);
            builduserDto.setFirstName(firstName);
            builduserDto.setLastName(lastName);
            builduserDto.setEmail(email);
            builduserDto.setPassword(password);
            builduserDto.setProfilePic(base64proPic);

            userService.saveUser(builduserDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatus getSelectedUser(@PathVariable("userId") String userId) {
        if (!RegexProcess.userIdMatcher(userId)) {
            return new SelectedUserAndNoteErrorStatus(1,"User id not allowed");
        }
        return userService.getSelectedUser(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        try {
            if (!RegexProcess.userIdMatcher(userId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping(value = "{userid}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateUser(UserDto userDto,
           @RequestPart("firstName") String firstName,
           @RequestPart("lastName") String lastName,
           @RequestPart("email") String email,
           @RequestPart("password") String password,
           @RequestPart("profilePic") MultipartFile profilePic,
           @PathVariable("userid") String userid
    ) {
        String base64proPic = "";

        try {
            byte[] byteProPic = profilePic.getBytes();
            base64proPic = AppUtil.generateProfilePicToBase64(byteProPic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Done : Build Object
        var builduserDto = new UserDto();
        builduserDto.setUserId(userid);
        builduserDto.setFirstName(firstName);
        builduserDto.setLastName(lastName);
        builduserDto.setEmail(email);
        builduserDto.setPassword(password);
        builduserDto.setProfilePic(base64proPic);

        userService.updateUser(userid,builduserDto);
    }
}
