package dk.kea.dat3js.hogwarts5.student;

import dk.kea.dat3js.hogwarts5.generic.PersonWithNames;
import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student implements PersonWithNames {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String firstName;
  private String middleName;
  private String lastName;
  @ManyToOne
  private House house;
  private Integer schoolYear; // 1-7
  private boolean prefect = false;
  private boolean male = true;

  public Student() {
  }

  public Student(String firstName, String lastName, boolean male, House house, int schoolYear) {
    this(firstName, null, lastName, male, house, schoolYear);
  }

  public Student(String firstName, String middleName, String lastName, Boolean male, House house, int schoolYear) {
    setFirstName(firstName);
    setMiddleName(middleName);
    setLastName(lastName);
    this.house = house;
    this.schoolYear = schoolYear;
    this.male = male != null ? male : true;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = capitalize(firstName);
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = capitalize(middleName);
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = capitalize(lastName);
  }

  public House getHouse() {
    return house;
  }

  public void setHouse(House house) {
    this.house = house;
  }

  public Integer getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(Integer schoolYear) {
    this.schoolYear = schoolYear;
  }

  public boolean isPrefect(){
    return prefect;
  }

  public void setPrefect(boolean prefect){
    if(house == null){
      return;
    }
    if(!prefect){
      house.removePrefect(this);
      this.prefect = false;
      return;
    }
    if(schoolYear >= 5){
      if(house.addPrefect(this)){
        this.prefect = true;
      }
    }
  }
  public boolean isMale(){
    return male;
  }

  public void setMale(boolean male){
    this.male = male;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getMiddleName(), student.getMiddleName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(getHouse().getName(), student.getHouse().getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getMiddleName(), getLastName(), getHouse().getName());
  }

}
