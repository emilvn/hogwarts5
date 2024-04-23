package dk.kea.dat3js.hogwarts5.student;

import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  private final StudentRepository studentRepository;
  private final HouseService houseService;

  public StudentService(StudentRepository studentRepository, HouseService houseService) {
    this.studentRepository = studentRepository;
    this.houseService = houseService;
  }

  public List<StudentResponseDTO> findAll() {
    return studentRepository.findAll().stream().map(this::toDTO).toList();
  }

  public Optional<StudentResponseDTO> findById(int id) {
    return studentRepository.findById(id).map(this::toDTO);
  }

  public List<StudentResponseDTO> findAllPrefects(){
    return studentRepository.findAllByPrefectIsTrue().stream().map(this::toDTO).toList();
  }

  public Optional<StudentResponseDTO> findPrefectById(int id){
    return studentRepository.findByPrefectTrueAndId(id).map(this::toDTO);
  }

  public List<StudentResponseDTO> findAllPrefectsByHouseName(String houseName){
    var house = houseService.findById(houseName).orElse(null);
    if(house == null){
      return new ArrayList<>();
    }
    return studentRepository
            .findByPrefectTrueAndHouse(house)
            .stream()
            .map(this::toDTO)
            .toList();
  }
  public StudentResponseDTO save(StudentRequestDTO student) {
    return toDTO(studentRepository.save(fromDTO(student)));
  }

  public Optional<StudentResponseDTO> updateIfExists(int id, StudentRequestDTO student) {
    if (studentRepository.existsById(id)) {
      Student existingStudent = fromDTO(student);
      existingStudent.setId(id);
      return Optional.of(toDTO(studentRepository.save(existingStudent)));
    } else {
      // TODO: Throw error?
      return Optional.empty();
    }
  }

  public Optional<StudentResponseDTO> partialUpdate(int id, StudentRequestDTO student) {
    Optional<Student> existingStudent = studentRepository.findById(id);
    if(existingStudent.isPresent()) {
      Student studentToUpdate = existingStudent.get();
      if(student.firstName() != null) {
        studentToUpdate.setFirstName(student.firstName());
      }
      if(student.middleName() != null) {
        studentToUpdate.setMiddleName(student.middleName());
      }
      if(student.lastName() != null) {
        studentToUpdate.setLastName(student.lastName());
      }
      if(student.isMale() != null){
        studentToUpdate.setMale(student.isMale());
      }
      if(student.isPrefect() != null){
        if(student.isPrefect()){
          setPrefect(id);
        } else {
          removePrefectStatus(id);
        }
      }
      if(student.house() != null) {
        studentToUpdate.setHouse(houseService.findById(student.house()).orElseThrow());
      }
      if(student.schoolYear() != null) {
        studentToUpdate.setSchoolYear(student.schoolYear());
      }
      return Optional.of(toDTO(studentRepository.save(studentToUpdate)));
    } else {
      // TODO: handle error
      return Optional.empty();
    }
  }

  public Optional<StudentResponseDTO> setPrefect(Integer id){
    var studentEntity = studentRepository.findById(id).orElse(null);
    if(studentEntity == null){
      return Optional.empty();
    }
    studentEntity.setPrefect(true);
    houseService.save(studentEntity.getHouse());
    return Optional.of(toDTO(studentRepository.save(studentEntity)));
  }

  public Optional<StudentResponseDTO> removePrefectStatus(Integer id){
    var studentEntity = studentRepository.findById(id).orElse(null);
    if(studentEntity == null){
      return Optional.empty();
    }
    studentEntity.setPrefect(false);
    houseService.save(studentEntity.getHouse());
    return Optional.of(toDTO(studentRepository.save(studentEntity)));
  }

  public Optional<StudentResponseDTO> deleteById(int id) {
    Optional<StudentResponseDTO> existingStudent = studentRepository.findById(id).map(this::toDTO);
    studentRepository.deleteById(id);
    return existingStudent;
  }

  public StudentResponseDTO toDTO(Student studentEntity) {
    return new StudentResponseDTO(
        studentEntity.getId(),
        studentEntity.getFirstName(),
        studentEntity.getMiddleName(),
        studentEntity.getLastName(),
        studentEntity.getFullName(),
        studentEntity.getHouse().getName(),
        studentEntity.getSchoolYear(),
        studentEntity.isPrefect()
    );
  }

  private Student fromDTO(StudentRequestDTO studentDTO) {
    Student entity = new Student(
        studentDTO.firstName(),
        studentDTO.middleName(),
        studentDTO.lastName(),
        studentDTO.isMale(),
        houseService.findById(studentDTO.house()).orElseThrow(),
        studentDTO.schoolYear()
    );

    if(studentDTO.name() != null){
      entity.setFullName(studentDTO.name());
    }

    return entity;
  }

  //TODO: /students skal ligeledes have en PATCH request for at tilføje/fjerne prefect udnævnelsen - men bruge samme regler som /prefects
}
