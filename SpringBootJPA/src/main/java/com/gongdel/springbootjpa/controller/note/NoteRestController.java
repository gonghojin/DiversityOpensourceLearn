package com.gongdel.springbootjpa.controller.note;

import com.gongdel.springbootjpa.common.exception.ResourceNotFoundException;
import com.gongdel.springbootjpa.domain.note.Note;
import com.gongdel.springbootjpa.repository.note.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteRestController {

    NoteRepository noteRepository;

    public NoteRestController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * @RequestBody annotation은 request body를 method parameter와 bind하기 위해서 사용된다.
     * @Valid annotation는 request body를 유효성 검사한다. [Note.class에 @NotBlank annotation이 할당된 title, content]
     * 만약 request body가 title or content를 가지고 있지 않다면, spring은 400 BadRequest error를 client에 return한다.
     */
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    /**
     * @return Note, 만약 주어진 id에 해당하는 값이 없다면 ResourceNotFoundException을 발생시킨다.
     * 이는 Spring Boot가 404 Not Found error를 client에게 return하게 한다.
     * [ResourceNotFoundException.class에 @ResponseStatus(value = HttpStatus.NOT_FOUND)를 annotation했기 때문]
     */
    @GetMapping("/notes/{id}")
    public Note getNoeteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@Valid @RequestBody Note note, @PathVariable(value = "id") Long noteId) {
        Note noteEntity = noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        noteEntity.setTitle(note.getTitle());
        noteEntity.setContent(note.getContent());

        Note updateEntity = noteRepository.save(noteEntity);

        return updateEntity;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNode(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        // 값이 없는 responseBody로 build
        return ResponseEntity.ok().build();
    }
}
