package com.isima.easynotes.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isima.easynotes.note.Note;
import com.isima.easynotes.repository.NoteRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.isima.easynotes.exception.NoSuchElementFoundException;

@RestController
@Tag(name="Note", description="the note API")
public class NoteController {
	@Autowired
    private final NoteRepository noteRepository;
	
	
	 public NoteController(NoteRepository noteRepository) {
		super();
		this.noteRepository = noteRepository;
	}

	@Operation(summary="Fetch all Notes", description="Fetch all notes entries and their data")
	@GetMapping("/Notes")
	    public List<Note> getAllNotes() {
	        return noteRepository.findAll();
	    }

	@Operation(summary="Fetch Notes by Id", description="Fetch a note by its Id")
	 @GetMapping("/Notes/{id}")
	    public Note findNoteById(@PathVariable long id)  {
		return noteRepository.findNoteById(id)
        		.orElseThrow(()->new NoSuchElementFoundException("id not found"));
	    }
	
	
	@Operation(summary="save a Note", description="Save a note") 
	 @PostMapping("/Notes")
	 public void ajouterNote(@RequestBody Note note) {
		 noteRepository.save(note); 
	 }

	@Operation(summary="update a note", description="update a note")
	 @PutMapping("/Notes")
	 public void modifierNote(@RequestBody Note note) {
		 noteRepository.save(note); 
	 }
	 
	@Operation(summary="delete a note", description="delete a note")
	 @DeleteMapping(value="/Notes/{id}")
	 public void supprimerNote(@PathVariable long id) {
		 noteRepository.deleteById(id);
	 }
	 
	@Operation(summary="Fetch Notes by title", description="Fetch a note by its title")
	 @GetMapping(value="/Notess/{title}")
	 public List<Note> findNotesByTitle(@PathVariable String title) {
	        return noteRepository.findByTitleContaining(title);
	    }
	 
	@Operation(summary="Fetch Notes by content", description="Fetch a note by its content")
	 @GetMapping(value="/NotesC/{c}")
	 public List<Note> findNotesByContent(@PathVariable String c) {
	        return noteRepository.findByContentContaining(c);
	    }

	@Operation(summary="tri Notes", description="Order notes by their creation date")
	 @GetMapping(value="/tri")
	 public List<Note> findAllByOrderByCreatedAtDesc() {
	        return noteRepository.findAllByOrderByCreatedAtDesc();
	    }
	 
	@Operation(summary="Fetch Notes by creation date before", description="Fetch Notes having a creation date before a given date")
	 @GetMapping(value="/Note/{date}")
	 public List<Note> findByCreatedAtBefore(LocalDateTime date) {
	        return noteRepository.findByCreatedAtBefore(date);
	    }
	 
	@Operation(summary="Fetch Notes by creation date after", description="Fetch Notes having a creation date after a given date")
	 @GetMapping(value="/NoteAfter/{days}")
	  public List<Note> getRecentlyUpdatedNotes(@PathVariable int days) {
	        LocalDateTime referenceDate = LocalDateTime.now().minusDays(days);
	        return noteRepository.findByUpdatedAtAfter(referenceDate);
	    }
	
	
	/*@ExceptionHandler(NoSuchElementFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNoSuchElementFoundException(
			NoSuchElementFoundException exception){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(exception.getMessage());
	}*/
	
}
