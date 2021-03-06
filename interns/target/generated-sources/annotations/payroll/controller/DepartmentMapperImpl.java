package payroll.controller;

import javax.annotation.Generated;
import payroll.dao.Department;
import payroll.dto.DepartmentDto;
import payroll.mappers.DepartmentMapper;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-01T19:53:36+0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department sourceToDestination(DepartmentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Department department = new Department();

        department.setName( dto.getName() );

        return department;
    }

    @Override
    public DepartmentDto destinationToSource(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setName( department.getName() );

        return departmentDto;
    }
}
