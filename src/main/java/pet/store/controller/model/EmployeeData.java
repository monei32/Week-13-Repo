package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Employee;

@Data
@NoArgsConstructor
public class EmployeeData {

	private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
	private String employeePhone;
	private String employeeJobTitle;
	private PetStoreEmployee petStore;
	
	
	public EmployeeData (Employee employee) {
		
		employeeId = employee.getEmployeeId();	
		employeeFirstName = employee.getEmployeeFirstName(); 
		employeeLastName = employee.getEmployeeLastName();
		employeePhone = employee.getEmployeePhone(); 
		employeeJobTitle = employee.getEmployeeJobTitle(); 
		petStore = new PetStoreEmployee(employee.getPetStore()); 
		
		
		for
				
	
	
	}
	
	
	@Data
	@NoArgsConstructor
	public static class PetStoreEmployee{
		
		private Long petStoreId;
		private String petStoreName;
		private String petStoreAddress;
		private String petStoreCity;
		private String petStoreState;
		private String petStoreZip;
		private String petStorePhone;
    
		

		
		
	}
	
}
