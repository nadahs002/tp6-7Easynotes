package com.isima.easynotes.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isima.easynotes.note.Note;

public interface NoteRepository  extends JpaRepository<Note,Long>{

	List<Note> findByTitleContaining(String title);
	List<Note> findByContentContaining(String c);
	List<Note> findAllByOrderByCreatedAtDesc();
	List<Note> findByCreatedAtBefore(LocalDateTime date);
	List<Note>  findByUpdatedAtAfter(LocalDateTime date);
	Optional<Note> findNoteById(long id);
	
	 
}
