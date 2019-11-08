package payroll.mappers;

import javax.annotation.Generated;
import payroll.dao.Employee;
import payroll.dto.EmployeeDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-08T17:32:37+0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee sourceToDestination(EmployeeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setName( dto.getName() );
        employee.setRole( dto.getRole() );
        employee.setWallet( dto.getWallet() );

        return employee;
    }

    @Override
    public EmployeeDto destinationToSource(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setName( employee.getName() );
        employeeDto.setRole( employee.getRole() );
        employeeDto.setWallet( employee.getWallet() );

        return employeeDto;
    }
}
