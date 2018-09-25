package org.jason.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.api.client.util.DateTime;

@Entity
@Table(name = "Event")
public class Event {
  @Id
  @GeneratedValue
  private Integer id;

  @Column(name = "start_time", nullable = false)
  private DateTime start;

  @Column(name = "end_time", nullable = false)
  private DateTime end;

  @Column(name = "summary", nullable = false)
  private String summary;

  @Column(name = "descripiton", nullable = false)
  private String description;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public DateTime getStart() {
    return start;
  }

  public void setStart(DateTime start) {
    this.start = start;
  }

  public DateTime getEnd() {
    return end;
  }

  public void setEnd(DateTime end) {
    this.end = end;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
