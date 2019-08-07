package com;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer commentId;

	@Transient
	private Integer fk;
	private String commentText;

	@ManyToOne
	private CompanyPost post;

	public Comment() {

	}

	public Comment(Integer commentId, String commentText, CompanyPost post) {
		this.commentId = commentId;
		this.commentText = commentText;
		this.post = post;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Integer getFk() {
		return fk;
	}

	public void setFk(Integer fk) {
		this.fk = fk;
	}

	@JsonIgnore
	public CompanyPost getPost() {
		return post;
	}

	public void setPost(CompanyPost post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [id=" + commentId + ", commentText=" + commentText + "]";
	}
}