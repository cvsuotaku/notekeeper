package ota.exam.notekeeper.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ota.exam.notekeeper.dto.NoteRequest;
import ota.exam.notekeeper.dto.NoteResponse;
import ota.exam.notekeeper.exception.NoteNotFoundException;
import ota.exam.notekeeper.model.Note;
import ota.exam.notekeeper.service.NoteService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static ota.exam.notekeeper.common.Constants.ERROR_MSG_NOTE_NOT_FOUND;
import static ota.exam.notekeeper.common.Constants.NOTE_CREATED;
import static ota.exam.notekeeper.common.Constants.NOTE_DELETED;
import static ota.exam.notekeeper.common.Constants.NOTE_RETRIEVED;
import static ota.exam.notekeeper.common.Constants.NOTE_RETRIEVED_ALL;
import static ota.exam.notekeeper.common.Constants.NOTE_UPDATED;
import static ota.exam.notekeeper.common.Constants.STATUS_CREATED;
import static ota.exam.notekeeper.common.Constants.STATUS_OK;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService {
    private final Set<Note> noteSet = new HashSet<>();


    @Override
    public NoteResponse createNote(NoteRequest request) {
        log.info("Creating a new note.");
        Note note = Note.builder()
                .id(UUID.randomUUID().toString())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        noteSet.add(note);
        log.info(NOTE_CREATED);
        return NoteResponse.builder()
                .status(STATUS_CREATED)
                .message(NOTE_CREATED)
                .data(note)
                .build();
    }

    @Override
    public NoteResponse retrieveAllNotes() {
        log.info("Retrieving all notes.");
        return NoteResponse.builder()
                .status(STATUS_OK)
                .message(NOTE_RETRIEVED_ALL)
                .data(noteSet)
                .build();
    }

    @Override
    public NoteResponse retrieveNoteById(String id) {
        log.info("Retrieving a note by Id.");
        Note note = noteSet.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException(ERROR_MSG_NOTE_NOT_FOUND));
        log.info(NOTE_RETRIEVED);
        return NoteResponse.builder()
                .status(STATUS_OK)
                .message(NOTE_RETRIEVED)
                .data(note)
                .build();
    }

    @Override
    public NoteResponse updateNoteById(String id, NoteRequest request) {
        log.info("Updating a note.");
        Note note = (Note) retrieveNoteById(id).getData();
        noteSet.remove(note);
        note = note.toBuilder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        noteSet.add(note);
        log.info(NOTE_UPDATED);
        return NoteResponse.builder()
                .status(STATUS_OK)
                .message(NOTE_UPDATED)
                .data(note)
                .build();
    }

    @Override
    public NoteResponse deleteNoteById(String id) {
        log.info("Deleting a note.");
        Note note = noteSet.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException(ERROR_MSG_NOTE_NOT_FOUND));
        noteSet.remove(note);
        log.info(NOTE_DELETED);
        return NoteResponse.builder()
                .status(STATUS_OK)
                .message(NOTE_DELETED)
                .build();
    }
}
