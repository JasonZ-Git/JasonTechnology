package org.jason.renting;

import java.util.Date;

public class UserComment {
  private Date commentDate;
  private String author;
  private String commentContent;
  private String status;
  private Integer approved;
  private Integer disapproved;

  public Date getCommentDate() {
    return commentDate;
  }

  public void setCommentDate(Date commentDateTime) {
    this.commentDate = commentDateTime;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getCommentContent() {
    return commentContent;
  }

  public void setCommentContent(String commentContent) {
    this.commentContent = commentContent;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getApproved() {
    return approved;
  }

  public void setApproved(Integer approved) {
    this.approved = approved;
  }

  public Integer getDisapproved() {
    return disapproved;
  }

  public void setDisapproved(Integer disapproved) {
    this.disapproved = disapproved;
  }

}
