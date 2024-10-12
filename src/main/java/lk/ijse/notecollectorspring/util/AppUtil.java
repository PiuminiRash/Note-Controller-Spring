package lk.ijse.notecollectorspring.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateNoteId() {
        return "Note"+ UUID.randomUUID();
    }

    public static String userId() {
        return "User"+ UUID.randomUUID();
    }

    public static String generateProfilePicToBase64(byte[] profilePic) {
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
