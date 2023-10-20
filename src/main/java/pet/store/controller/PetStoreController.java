
// Handles HTTP Requests and passes off data to the service layer. 

//HTTP is just test that is sent over the internet. HTTP consists of a request and a response. Request Line Request Header Request Body =Json 

//Request Line Consists of VERB + UTI + HTTP Verion ex GET /pet_store HTTP/2.0      POST /pet_park HTTP/2.0   Common HTTP VERBS (CRUD)" POST CREATE GET READ PUT UPDATE DELETE DELETE

// Header Describe the request and allowed Response format(s) 

// Request Body used for POST CREATE and PUT UPDATe 

// HTTP Response has a HTTP Version +status code + reason 200 ok 404 not found 409 no conflict

package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;

	@PostMapping("/pet_store")
	@ResponseStatus(HttpStatus.CREATED)
	public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating PetStore {}", petStoreData);
		var updated = petStoreService.savePetStore(petStoreData);
		return updated;
	}

	@GetMapping("/pet_store")
	public List<PetStoreData> retrieveAllPetStores() {
		log.info("Retrieve all petstores called");
		var found = petStoreService.retrieveAllPetStores();
		return found;
	}

	@GetMapping("/pet_store/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		// the open and close curly braces indicate a replaceable parameter and the
		// paramater is the petstrore id
		log.info("Retrieving PetSoters with Id ={}", petStoreId);
		return petStoreService.retrievePetStoreByID(petStoreId);
	}

	// inside the put mapping is the resource inside the source variable will match
	// the variable

	@PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		// Set the pets store ID in the pet store data from the ID parameter
		petStoreData.setPetStoreId(petStoreId);
		// Log the Method Call
		log.info("Updating pet store with ID {}", petStoreData);

		// Call the savePet Store Method
		return petStoreService.savePetStore(petStoreData);
	}

	@DeleteMapping("/pet_store/")
	public void deletePetStores() {
		log.info("Attempting to delete all Pet Stores");
		throw new UnsupportedOperationException("Deleting all Pet Stores is not allowend!!");
	}

	@DeleteMapping("/pet_store/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {

		log.info("Deleting pet store with ID = {}", petStoreId);

		petStoreService.deletePetStoreById(petStoreId);

		return Map.of("message", "Deletion of pet store with ID=" + petStoreId);
	}

	@PostMapping("/pet_store/{petStoreId}/employee")
	@ResponseStatus(HttpStatus.CREATED)
	public PetStoreEmployee insertEmployeeData(@PathVariable Long petStoreId,
			@RequestBody PetStoreEmployee petStoreEmployee) {

		log.info("Creating employee {} for pet store with ID={}", petStoreEmployee, petStoreId);

		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
          
	}

	@PostMapping("/pet_store/{petStoreId}/customer")
	@ResponseStatus(HttpStatus.CREATED)
	public PetStoreCustomer insertCustomerData(@PathVariable Long petStoreId,
			@RequestBody PetStoreCustomer petStorecustomer) {
		log.info("Creating customer {} for pet store with ID={}", petStorecustomer, petStoreId);

		return petStoreService.saveCustomer(petStoreId, petStorecustomer); 
	}

}
