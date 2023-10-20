
// Takes data from the controller that handles the bussinnes layer and then passes the data to the DAO object. 


package pet.store.service;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;


@Slf4j
@Service
public class PetStoreService {

	@Autowired
	private PetStoreDao petStoreDao;
	
	@Autowired 
	private EmployeeDao employeeDao; 
	
	@Autowired
	private CustomerDao customerDao; 
	

    
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId(); 
		PetStore petStore = findOrCreatePetStore(petStoreId); 
		
		copyPetStoreFields(petStore,petStoreData); 
		return new PetStoreData(petStoreDao.save(petStore)); 
	}

	
	
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreId(petStoreData.getPetStoreId());
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		
	}



	private PetStore findOrCreatePetStore(Long petStoreId) {
		if(Objects.isNull(petStoreId)) {
			return new PetStore(); 
		} 
		else {
		return findPetStoreById(petStoreId); 
		}
	}



	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("Pet Store with ID=" + petStoreId + "was not found")); 
		
		
	}



	// First list tells the DAO to return the Enity Object. 
	// Second List <PetStoreData> turns the list of enity objects into a list of petStoreData
	@Transactional(readOnly = true)
	public List<PetStoreData> retrieveAllPetStores() {
		List<PetStore> petStores = petStoreDao.findAll();
		List<PetStoreData> response = new LinkedList<>(); 
		
		
		// turns the list of petStore Enity's into PetStoreData
		
		for(PetStore petstore : petStores) {
			response.add(new PetStoreData(petstore)); 
		}
		
		return response;
	}
    
	@Transactional(readOnly = true)
	public PetStoreData retrievePetStoreByID(Long petStoreId) {
		PetStore petStore = findOrCreatePetStore(petStoreId);
		return new PetStoreData(petStore);
	}
    
	@Transactional(readOnly = false)
	public void deletePetStoreById(Long petStoreId) {
		PetStore petStore = findOrCreatePetStore(petStoreId); 
		petStoreDao.delete(petStore);
		
	}
	
    @Transactional(readOnly= false)
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
    	PetStore petStore = findPetStoreById(petStoreId); 
    	
    	Long employeeId = petStoreEmployee.getEmployeeId();
    	Employee employee = findOrCreateEmployee(employeeId, petStoreId); 
    	
    	setEmployeeFields(employee, petStoreEmployee); 
    	employee.setPetStore(petStore); 
    	
    	petStore.getEmployees().add(employee); 
    	
    	return new PetStoreEmployee(employeeDao.save(employee)); 
    	 
		}

    
    
    
	private void setEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
		employee.setEmployeeId(petStoreEmployee.getEmployeeId()); 
		employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
		employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
		employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
		employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
		
	}

	private Employee findOrCreateEmployee(Long employeeId, Long petStoreId) {
		Employee employee;

	//	PetStore petStore = findOrCreatePetStore(petStoreId); 
		
		// No employee found then create a new employee
		// set the pet store as its parent
		if (Objects.isNull(employeeId)) {
			employee = new Employee();
			log.info("Create Employee with id {}" + employeeId); 
		} 
		// Otherwise load the employee and its pet store linkage.
		else {
			employee = employeeDao.findById(employeeId)
					.orElseThrow(() -> new NoSuchElementException("Employee with Id =" + employeeId + "was not found"));
		}

		return employee;
	}
      @Transactional(readOnly = false)
	public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
		PetStore petStore = findPetStoreById(petStoreId); 
		
		Long customerId = petStoreCustomer.getCustomerId(); 
		Customer customer = findOrCreateCustomer(customerId, petStoreId); 
		setCustomerFields(customer, petStoreCustomer); 
		
		return new PetStoreCustomer(customerDao.save(customer));
	}



	private void setCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
		    customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
		    customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
		    customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
		    customer.setCustomerId(petStoreCustomer.getCustomerId());
		    
		
	}



	private Customer findOrCreateCustomer(Long customerId, Long petStoreId) {
		Customer customer; 

		//	PetStore petStore = findOrCreatePetStore(petStoreId); 
			
			// No employee found then create a new employee
			// set the pet store as its parent
			if (Objects.isNull(customerId)) {
				customer = new Customer(); 
				log.info("Create Customer with ID {}" + customerId);
			} 
			// Otherwise load the employee and its pet store linkage.
			else {
				customer = customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer with Id =" + customerId + "was not found")); 
		
	}
			return customer;
	}
	
}