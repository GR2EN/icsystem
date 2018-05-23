package net.gr2en.icsystem.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "computer_id")
  private Computer computer;

  @ManyToOne
  @JoinColumn(name = "service_id")
  private Service service;

  private int numberOfHours;

  private Date date;

  public Order() {

  }

  public Order(Integer id, User user, Computer computer, Service service, int numberOfHours,
      Date date) {
    this.id = id;
    this.user = user;
    this.computer = computer;
    this.service = service;
    this.numberOfHours = numberOfHours;
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

  public int getNumberOfHours() {
    return numberOfHours;
  }

  public void setNumberOfHours(int numberOfHours) {
    this.numberOfHours = numberOfHours;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
