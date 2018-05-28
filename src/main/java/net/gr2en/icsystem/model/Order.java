package net.gr2en.icsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @NotBlank
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "computer_id")
  private Computer computer;

  @NotBlank
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "service_id")
  private Service service;

  @NotBlank
  private int hours;

  private Date date;

  public Order() {

  }

  public Order(Integer id, User user, Computer computer, Service service, int hours,
      Date date) {
    this.id = id;
    this.user = user;
    this.computer = computer;
    this.service = service;
    this.hours = hours;
    this.date = date;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Computer getComputer() {
    return computer;
  }

  public void setComputer(Computer computer) {
    this.computer = computer;
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public int getHours() {
    return hours;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
