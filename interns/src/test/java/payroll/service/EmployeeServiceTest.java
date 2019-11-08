package payroll.service;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import payroll.dao.Department;
import payroll.dao.Employee;
import payroll.dto.EmployeeDto;
import payroll.dto.OrderDto;
import payroll.mappers.EmployeeMapper;
import payroll.repository.DepartmentRepository;
import payroll.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository mockrep;

    @Mock
    OrderService mockorder;

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    EmployeeMapper employeeMapper;


    @InjectMocks
    EmployeeServices mockserv;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void takeAllTest() {
        when(mockrep.findAll()).thenReturn(Arrays.asList(new Employee(1L, "Dan", "Prog", 1000, new Department()), new Employee(2L, "Dan", "Prog", 1000, new Department())));
        List<Employee> employees = mockserv.takeAll();
        Assertions.assertEquals(1, employees.get(0).getId());
        Assertions.assertEquals(2, employees.get(1).getId());
    }

    @Test
    public void takeByNameTest() {
        when(mockrep.findByName("Dan")).thenReturn(Arrays.asList(new Employee(1L, "Dan", "Prog", 1000, new Department())));
        List<Employee> employees = mockserv.takeByName("Dan");
        Assertions.assertEquals("Dan", employees.get(0).getName());
    }

    @Test
    public void takeOneTest() {
        when(mockrep.findById(1L)).thenReturn(java.util.Optional.of(new Employee(1L, "Dan", "Prog", 1000, new Department("РБ"))));
        Department department = mockserv.takeOne(1L);
        Assertions.assertEquals("РБ", department.getName());
    }

    @Test
    public void newEmployeeTest() {
        EmployeeDto dto = new EmployeeDto("Dan", "Prog", 1000, "РБ");
        when(employeeMapper.sourceToDestination(dto)).thenReturn(new Employee(1L, "Dan", "Prog", 1000, new Department("РБ")));
        Employee employee = employeeMapper.sourceToDestination(dto);
        when(mockrep.save(employee)).thenReturn(employee);
        mockrep.save(employee);
        Assertions.assertEquals(1L, employee.getId());
        Assertions.assertEquals(1000, employee.getWallet());
        Assertions.assertEquals("Dan", employee.getName());
        verify(employeeMapper, times(1)).sourceToDestination(dto);
        verify(mockrep, times(1)).save(employee);

    }

    @Test
    public void setOrderTest() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Dan");
        employee.setWallet(10000);
        OrderDto dto = new OrderDto();
        dto.setCost(1000);
        dto.setOrderName("Burger");
        when(mockrep.findById(1L)).thenReturn(java.util.Optional.of(employee));
        when(mockrep.save(employee)).thenReturn(employee);
        when(mockorder.postOrder(dto)).thenReturn(ResponseEntity.ok("Ok"));
        if (dto.getClientName() == null) {
            dto.setClientName(mockrep.findById(1L).get().getName());
        }
        mockrep.findById(1l).map(employee1 -> {
            employee.setWallet(mockrep.findById(1l).get().getWallet() - dto.getCost());
            return employee;
        });
        mockorder.postOrder(dto);

        Assertions.assertEquals(mockorder.postOrder(dto), ResponseEntity.ok("Ok"));
        Assertions.assertEquals("Dan", dto.getClientName());
        Assertions.assertEquals(9000, employee.getWallet());
        verify(mockrep, times(3)).findById(1l);
        verify(mockorder, times(2)).postOrder(dto);
    }

    @Test
    public void employeeDeletetets() {
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee(1L, "Dan", "Prog", 1000, new Department("РБ")));
//        employees.add(new Employee(2L, "Dan", "Prog", 1000, new Department("РБ")));
        mockserv.removeEmployee(1l);
        verify(mockrep, times(1)).deleteById(1l);
    }


    @Test
    public void updateEmplyeeTest() {
        when(departmentRepository.findByName("РБ")).thenReturn(new Department("РБ"));
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Dan");
        employee.setRole("Prog");
        employee.setWallet(10000);
        employee.setDepartment(departmentRepository.findByName("РБ"));
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Al");
        employeeDto.setRole("Mage");
        employeeDto.setWallet(1200);

        when(mockrep.findById(1l)).thenReturn(java.util.Optional.of(employee));
        when(mockrep.save(employee)).thenReturn(employee);
        when(employeeMapper.sourceToDestination(employeeDto)).thenReturn(employee);
        mockserv.updateEmployee(employeeDto, 1l);

        Assertions.assertEquals("Al", employee.getName());
        Assertions.assertEquals("Mage", employee.getRole());
        Assertions.assertEquals(1200, employee.getWallet());
        verify(departmentRepository, times(1)).findByName("РБ");
//        verify(mockserv,times(1)).updateEmployee(employeeDto,1l);
    }


}

