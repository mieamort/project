package payroll.service;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import payroll.dao.Department;
import payroll.dao.Employee;
import payroll.dto.DepartmentDto;
import payroll.mappers.DepartmentMapper;
import payroll.repository.DepartmentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DepartmentServiceTest {

    @Mock
    DepartmentMapper mapper;

    @Mock
    DepartmentRepository repository;

    @InjectMocks
    DepartmentService mockserv;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createDepartmentTest() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("РБ");
        when(mapper.sourceToDestination(departmentDto)).thenReturn(new Department(1l, "РБ", new ArrayList<Employee>()));
        Department department = mapper.sourceToDestination(departmentDto);
        when(repository.save(department)).thenReturn(department);
        repository.save(department);
        Assertions.assertEquals("РБ", department.getName());
        Assertions.assertEquals(1l, department.getId());
        verify(repository, times(1)).save(department);

    }

    @Test
    public void takeAllTest() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Department(1l, "РБ", new ArrayList<>()), new Department(2l, "Techno", new ArrayList<>())));
        List<Department> departments = mockserv.takeAll();

        Assertions.assertEquals(1l, departments.get(0).getId());
        Assertions.assertEquals(2l, departments.get(1).getId());

    }

    @Test
    public void takeByNameTest() {
        when(repository.findByName("РБ")).thenReturn(new Department(1l, "РБ", new ArrayList<>()));
        Department department = mockserv.takeByName("РБ");
        Assertions.assertEquals("РБ", department.getName());
    }
}
