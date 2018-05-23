package net.gr2en.icsystem.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "computers")
public class Computer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String status;

  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "dd.MM.yyyy")
  private Date lastMaintenance;

  @ManyToMany
  @JoinTable(
      name = "computer_software",
      joinColumns = @JoinColumn(name = "computer_id"),
      inverseJoinColumns = @JoinColumn(name = "software_id")
  )
  private Set<Software> software = new HashSet<>();

  public Computer(Integer id, String status, Date lastMaintenance) {
    this.id = id;
    this.status = status;
    this.lastMaintenance = lastMaintenance;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getLastMaintenance() {
    return lastMaintenance;
  }

  public void setLastMaintenance(Date lastMaintenance) {
    this.lastMaintenance = lastMaintenance;
  }

  public Set<Software> getSoftware() {
    return software;
  }

  public void setSoftware(Set<Software> software) {
    this.software = software;
  }
}
