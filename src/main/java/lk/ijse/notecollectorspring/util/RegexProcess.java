/* Created By Sithira Roneth
 * Date :9/30/24
 * Time :16:05
 * Project Name :Note_Collector
 * */
package lk.ijse.notecollectorspring.util;

import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean noteIdMatcher(String noteId) {
        String regexForNoteId = "^Note[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForNoteId);
        return regexPattern.matcher(noteId).matches();
    }
    public static boolean userIdMatcher(String userId) {
        String regexForUserId = "^User[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserId);
        return regexPattern.matcher(userId).matches();
    }
}
