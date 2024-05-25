package ota.exam.notekeeper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ota.exam.notekeeper.dto.NoteRequest;
import ota.exam.notekeeper.dto.NoteResponse;
import ota.exam.notekeeper.service.NoteService;

import static ota.exam.notekeeper.common.Constants.ERROR_MSG_NOTE_NOT_FOUND;
import static ota.exam.notekeeper.common.Constants.ERROR_MSG_REQUEST_PROBLEM;
import static ota.exam.notekeeper.common.Constants.NOTE_CREATED;
import static ota.exam.notekeeper.common.Constants.NOTE_DELETED;
import static ota.exam.notekeeper.common.Constants.NOTE_RETRIEVED;
import static ota.exam.notekeeper.common.Constants.NOTE_RETRIEVED_ALL;
import static ota.exam.notekeeper.common.Constants.NOTE_UPDATED;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    @Operation(summary = "Create a new note.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = NOTE_CREATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input request",
                    content = @Content)})
    public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody NoteRequest request) {
        return ResponseEntity.ok(noteService.createNote(request));
    }

    @GetMapping
    @Operation(summary = "Retrieve all notes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = NOTE_RETRIEVED_ALL,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = ERROR_MSG_REQUEST_PROBLEM,
                    content = @Content)})
    public ResponseEntity<NoteResponse> retrieveAllNotes() {
        return ResponseEntity.ok(noteService.retrieveAllNotes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a specific note by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = NOTE_RETRIEVED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = ERROR_MSG_REQUEST_PROBLEM,
                    content = @Content),
            @ApiResponse(responseCode = "404", description = ERROR_MSG_NOTE_NOT_FOUND,
                    content = @Content)})
    public ResponseEntity<NoteResponse> retrieveNoteById(@PathVariable String id) {
        return ResponseEntity.ok(noteService.retrieveNoteById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a specific note.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = NOTE_UPDATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = ERROR_MSG_REQUEST_PROBLEM,
                    content = @Content),
            @ApiResponse(responseCode = "404", description = ERROR_MSG_NOTE_NOT_FOUND,
                    content = @Content)})
    public ResponseEntity<NoteResponse> updateNoteById(@PathVariable String id, @RequestBody NoteRequest request) {
        return ResponseEntity.ok(noteService.updateNoteById(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a specific note.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = NOTE_DELETED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = ERROR_MSG_REQUEST_PROBLEM,
                    content = @Content),
            @ApiResponse(responseCode = "404", description = ERROR_MSG_NOTE_NOT_FOUND,
                    content = @Content)})
    public ResponseEntity<NoteResponse> deleteNoteById(@PathVariable String id) {
        return ResponseEntity.ok(noteService.deleteNoteById(id));
    }
}
