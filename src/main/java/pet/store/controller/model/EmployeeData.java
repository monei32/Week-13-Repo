//package pet.store.controller.model;
//
//import java.util.HashSet;
//import java.util.Set;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import pet.store.entity.Employee;
//import pet.store.entity.PetStore;
//
//@Data
//@NoArgsConstructor
////public class EmployeeData {
//
//	private Long employeeId;
//	private String employeeFirstName;
//	private String employeeLastName;
//	private String employeePhone;
//	private String employeeJobTitle;
//
//
//	public EmployeeData(Employee employee) {
//		employeeId = employee.getEmployeeId();
//		employeeFirstName = employee.getEmployeeFirstName();
//		employeeLastName = employee.getEmployeeLastName();
//		employeePhone = employee.getEmployeePhone();
//		employeeJobTitle = employee.getEmployeeJobTitle();
//	}
//
//	// DTO
//	@Data
//	@NoArgsConstructor
//	public static class PetStoreEmployee {
//		private Long petStoreId;
//		private String petStoreName;
//		private String petStoreAddress;
//		private String petStoreCity;
//		private String petStoreState;
//		private String petStoreZip;
//		private String petStorePhone;
//
//		
//		private Long employeeId;
//		private String employeeFirstName;
//		private String employeeLastName;
//		private String employeePhone;
//		private String employeeJobTitle;
//		
//		public PetStoreEmployee(Employee employee) {
//			
//			PetStore petStore = employee.getPetStore();
//			petStoreId = petStore.getPetStoreId();
//			petStoreName = petStore.getPetStoreName();
//			petStoreAddress = petStore.getPetStoreAddress();
//			petStoreCity = petStore.getPetStoreCity();
//			petStoreState = petStore.getPetStoreState();
//			petStoreZip = petStore.getPetStoreZip();
//			petStorePhone = petStore.getPetStorePhone();
//			
//			employeeId = employee.getEmployeeId();
//			employeeFirstName = employee.getEmployeeFirstName();
//			employeeLastName = employee.getEmployeeLastName();
//			employeePhone = employee.getEmployeePhone();
//			employeeJobTitle = employee.getEmployeeJobTitle();
//			
//			
//
//		}
//
//	}
//    @Data
//    @NoArgsConstructor
//	public static class Customer {
//
//		private Long customerId;
//		private String customerFirstName;
//		private String customerLastName;
//		private String customerEmail;
//        
//		
//		
//	}
//
//}
