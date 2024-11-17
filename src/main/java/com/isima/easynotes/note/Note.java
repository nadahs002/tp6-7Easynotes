package com.isima.easynotes.note;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;


@Entity  // entity : classe note est une entité jpa
@Table(name = "notes")  // table de la bdd : notes
@EntityListeners(AuditingEntityListener.class)  // generer automatiquement les champs de la date et heure
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters =true)
public class Note {
	@Id  // id est la clé primaire de l'entité
	@GeneratedValue(strategy = GenerationType.AUTO)  // id est générer automatiquement
	private long id;
	@NotBlank(message = "La valeur titre ne peut pas être vide")  // le champs ne doit pas etre vide sinon envoyer le message d'erreur 
	private String title;
	@NotBlank(message = "La valeur content ne peut pas être vide")
	private String content;
	@Column(nullable=false, updatable=false)  // createdAt ne doit pas etre vide et non modifiable
	@Temporal(TemporalType.TIMESTAMP)  // date avec l'heure
	@CreatedDate  // avoir la date et heure de la creation de l'entité
	private Date createdAt;
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate  // metre a jour updatedAt a chaque foir que l'entité est modifié
	private Date updatedAt;
	
	
	public Note(long id, @NotBlank(message = "La valeur titre ne peut pas être vide") String title,
			@NotBlank(message = "La valeur content ne peut pas être vide") String content, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
	
	

}
