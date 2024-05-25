package ota.exam.notekeeper.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String STATUS_CREATED = HttpStatus.CREATED.toString();
    public static final String STATUS_OK = HttpStatus.OK.toString();
    public static final String STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST.toString();
    public static final String STATUS_NOT_FOUND = HttpStatus.NOT_FOUND.toString();
    public static final String ERROR_MSG_REQUEST_PROBLEM = "There's a problem in your request.";
    public static final String ERROR_MSG_NOTE_NOT_FOUND = "Note not found.";
    public static final String NOTE_CREATED = "Note successfully created.";
    public static final String NOTE_RETRIEVED_ALL = "All Notes successfully retrieved.";
    public static final String NOTE_RETRIEVED = "Successfully retrieved a note by Id.";
    public static final String NOTE_UPDATED = "Successfully updated a note.";
    public static final String NOTE_DELETED = "Successfully deleted / removed a note.";
}
