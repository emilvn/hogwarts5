package dk.kea.dat3js.hogwarts5.house;

import dk.kea.dat3js.hogwarts5.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class House {
  @Id
  private String name;
  private String founder;
  private String color1;
  private String color2;

  @OneToMany @Transient
  private List<Student> prefects = new ArrayList<>();

  public House() {}

  public House(String name, String founder, String[] colors) {
    this.name = name;
    this.founder = founder;
    this.color1 = colors[0];
    this.color2 = colors[1];
  }

  public String getName() {
    return name;
  }

  public String getFounder() {
    return founder;
  }

  public List<Student> getPrefects(){
    return prefects;
  }

  public void setPrefects(List<Student> prefects){
    this.prefects = prefects;
  }

  public boolean addPrefect(Student prefect){
    if(prefect == null){
      return false;
    }
    if(!name.equals(prefect.getHouse().getName())){
      return false;
    }
    if(prefect.getSchoolYear() < 5){
      return false;
    }
    if(prefects.isEmpty()){
      prefects.add(prefect);
      return true;
    }
    if(prefects.size() == 1){
      if(prefects.getFirst().isMale() != prefect.isMale()){
        prefects.add(prefect);
        return true;
      }
    }
    return false;
  }

  public void removePrefect(Student prefect){
    prefects = prefects
            .stream()
            .filter(p -> p.getId() != prefect.getId())
            .collect(Collectors.toList());
  }

  public String[] getColors() {
    return new String[] {color1, color2};
  }
}
