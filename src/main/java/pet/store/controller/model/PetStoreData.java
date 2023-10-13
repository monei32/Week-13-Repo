
// Class that represents a pet store, customers and employees. 
// The PetStore Data class has a constructor that take a PetStore Object as a parameter. The constuctor copies the values of the PetStore objnect to the corresponding fiels of the PetStoreData Object. 
// The Constructor also creates PetStoreCustomer and PetStoreEmployee objects for each of the customer and employees of the pet store respectvly. 
// Class is used to Manage pet store data in a database. 
// to exchange pet store data between two different applications. 

// No Recurvise Entitiy that cause jackson to blow up. 
// 



package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {

	private Long petStoreId;
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	private Set<PetStoreCustomer> customers = new HashSet<>();
	private Set<PetStoreEmployee> employees = new HashSet<>();

	public PetStoreData(PetStore petStore) {
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreAddress = petStore.getPetStoreAddress();
		petStoreCity = petStore.getPetStoreCity();
		petStoreState = petStore.getPetStoreState();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();

		for (Customer customer : petStore.getCustomers()) {
			customers.add(new PetStoreCustomer(customer));
		}

		for (Employee employee : petStore.getEmployees()) {
			employees.add(new PetStoreEmployee(employee));
		}
	}

	@Data
	@NoArgsConstructor
	public static class PetStoreCustomer {

		private Long customerId;
		private String customerFirstName;
		private String customerLastName;
		private String customerEmail;

		PetStoreCustomer(Customer customer) {
			customerId = customer.getCustomerId();
			customerFirstName = customer.getCustomerFirstName();
			customerLastName = customer.getCustomerLastName();
			customerEmail = customer.getCustomerEmail();

		}

	}

	@Data
	@NoArgsConstructor
	public static class PetStoreEmployee {

		private Long employeeId;
		private String employeeFirstName;
		private String employeeLastName;
		private String employeePhone;
		private String employeeJobTitle;

		PetStoreEmployee(Employee employee) {

		}

	}

}
