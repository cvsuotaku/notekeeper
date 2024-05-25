package ota.exam.notekeeper.service;

import ota.exam.notekeeper.dto.NoteRequest;
import ota.exam.notekeeper.dto.NoteResponse;

public interface NoteService {
    NoteResponse createNote(NoteRequest request);
    NoteResponse retrieveAllNotes();
    NoteResponse retrieveNoteById(String id);
    NoteResponse updateNoteById(String id, NoteRequest request);
    NoteResponse deleteNoteById(String id);
}
