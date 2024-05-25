package ota.exam.notekeeper.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequest {
    @NotEmpty
    @Size(max = 25)
    private String title;
    @NotEmpty
    private String content;
}
