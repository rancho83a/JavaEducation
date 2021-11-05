package bg.softuni.hateoas.web;

import bg.softuni.hateoas.config.OrderMapper;
import bg.softuni.hateoas.config.StudentMapper;
import bg.softuni.hateoas.model.dto.OrderDto;
import bg.softuni.hateoas.model.dto.StudentDto;
import bg.softuni.hateoas.model.entity.OrderEntity;
import bg.softuni.hateoas.model.entity.StudentEntity;
import bg.softuni.hateoas.repository.StudentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RequestMapping("/students")
@RestController
public class StudentsController {

    // WARNING: Normally we never inject repos in the controllers,
    // we do this just for test and fun here.
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final OrderMapper orderMapper;


    public StudentsController(StudentRepository studentRepository, StudentMapper studentMapper, OrderMapper orderMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDto>>> getOrders(
            @PathVariable ("id") Long id
    ){

        StudentEntity student = this.studentRepository.findById(id).orElseThrow();
        List<EntityModel<OrderDto>> orders = student.getOrders()
                .stream()
                .map(o->  orderMapper.mapEntityToDto(o))
              //  .map(this::map)
                .map(EntityModel::of)
                //.map(dto -> EntityModel.of(dto))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(orders));
    }

    private OrderDto map(OrderEntity order){
        return new OrderDto().setId(order.getId()).setStudentId(order.getStudent().getId()).setCourseId(order.getCourse().getId());
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<EntityModel<StudentDto>>> getStudents() {

        List<EntityModel<StudentDto>> allStudents = this.studentRepository.findAll()
                .stream()
                .map(studentMapper::mapEntityToDto)
                .map(dto-> EntityModel.of(dto, createStudentLink(dto)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(allStudents));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDto>> getStudentsById(
            @PathVariable ("id") Long id) {
        StudentDto student = this.studentRepository.findById(id)
                // .map(s-> studentMapper.mapEntityToDto(s))
                .map(studentMapper::mapEntityToDto)
                .orElseThrow();


        return ResponseEntity.ok(
                EntityModel.of(student, createStudentLink(student))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDto>> update(
            @PathVariable  Long id, StudentDto studentDto
    ){

        //IMPLEMENTATION not important for the demo

        return ResponseEntity.ok().build();
    }

    private Link[] createStudentLink(StudentDto studentDto) {
        List<Link> result = new ArrayList<>();

        Link selfLink = linkTo(methodOn(StudentsController.class).getStudentsById(studentDto.getId())).withSelfRel();

        Link updateLink = linkTo(methodOn(StudentsController.class)
                .update(studentDto.getId(), studentDto)).withRel("update");


        // TODO ordersLink

        Link ordersLink = linkTo(methodOn(StudentsController.class)
                .getOrders(studentDto.getId())).withRel("orders");

        result.add(selfLink);
        result.add(updateLink);
        result.add(ordersLink);
        return result.toArray(new Link[0]);
    }
}
