package dk.kea.dat3js.hogwarts5.teacher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

  private final TeacherService teacherService;

  public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @GetMapping
  public List<TeacherResponseDTO> getAllTeachers() {
    return teacherService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeacherResponseDTO> getTeacher(@PathVariable int id) {
    return ResponseEntity.of(teacherService.findById(id));
  }

  @PostMapping
  public ResponseEntity<TeacherResponseDTO> createTeacher(@RequestBody TeacherRequestDTO teacher) {
    return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.save(teacher));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable int id, @RequestBody TeacherRequestDTO teacher) {
    return ResponseEntity.of(teacherService.updateIfExists(id, teacher));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<TeacherResponseDTO> partialUpdateTeacher(@PathVariable int id, @RequestBody TeacherRequestDTO teacher) {
    return ResponseEntity.of(teacherService.partialUpdate(id, teacher));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<TeacherResponseDTO> deleteTeacher(@PathVariable int id) {
    return ResponseEntity.of(teacherService.deleteById(id));
  }
}
