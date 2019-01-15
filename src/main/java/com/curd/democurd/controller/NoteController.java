package com.curd.democurd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.curd.democurd.model.Note;
import com.curd.democurd.repository.NoteRepository;

import java.sql.Date;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/notes/{id}")
    public Note getByIdNotes(@PathVariable(value = "id") long id) {
        Note noteupdate = noteRepository.getOne(id);
        return noteupdate;
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        Date date = new Date(0);

        note.setCreatedAt(date);
        note.setUpdatedAt(date);
        return noteRepository.save(note);
    }

    @PutMapping("/notes/{id}")
    public Note update(@PathVariable(value = "id") long id, @RequestBody Note _Note) {
        Note noteupdate = noteRepository.getOne(id);
        noteupdate.setTitle(_Note.getTitle());
        noteupdate.setContent(_Note.getContent());
        return noteRepository.save(noteupdate);
    }

    @DeleteMapping("/notes/{id}")
    public String createNote(@PathVariable(value = "id") long id) {
        noteRepository.deleteById(id);
        return "Deleted!!";
    }
}
