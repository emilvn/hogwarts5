package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String firstName;
  private String middleName;
  private String lastName;
  @ManyToOne
  private House house;
  private Integer schoolYear; // 1-7

  public Student() {
  }

  public Student(String firstName, String lastName, House house, int schoolYear) {
    this(firstName, null, lastName, house, schoolYear);
  }

  public Student(String firstName, String middleName, String lastName, House house, int schoolYear) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.house = house;
    this.schoolYear = schoolYear;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return capitalize(firstName);
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return capitalize(middleName);
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return capitalize(lastName);
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    return getMiddleName() == null ? getFirstName() + " " + getLastName() : getFirstName() + " " + getMiddleName() + " " + getLastName();
  }

  public String capitalize(String s){
    if(s == null){
      return null;
    }
    if(s.isEmpty()){
      return s;
    }
    s = s.trim();
    if(s.contains(" ")){
      var parts = s.split(" ");
      var capitalizedList = Arrays.stream(parts).map(this::capitalize).toList();
      return String.join(" ", capitalizedList);
    }
    return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
  }

  public void setFullName(String fullName){
    if(fullName == null){
      return;
    }

    int firstSpace = fullName.indexOf(" ");
    int lastSpace = fullName.lastIndexOf(" ");

    if(firstSpace == -1){
      setFirstName(fullName);
      setMiddleName(null);
      setLastName(null);
    }
    else if(firstSpace == lastSpace){
      setFirstName(fullName.substring(0, firstSpace));
      setMiddleName(null);
      setLastName(fullName.substring(lastSpace+1));
    }
    else {
      setFirstName(fullName.substring(0, firstSpace));
      setMiddleName(fullName.substring(firstSpace+1, lastSpace));
      setLastName(fullName.substring(lastSpace+1));
    }
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
