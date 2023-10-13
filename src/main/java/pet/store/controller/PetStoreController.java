package pet.store.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return petStoreService.savePetStore(petStoreData);
	}
    
	@GetMapping 
	public List<PetStoreData> retrieveAllPetStores(){
		log.info("Retrieve all petstores called"); 
		return petStoreService.retrieveAllPetStores(); 
	}
	
	
	// inside the put mapping is the resource inside the source variable will match the variable
	
	@PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		// Set the pets store ID in the pet store data from the ID parameter
		petStoreData.setPetStoreId(petStoreId);
	// Log the Method Call
	 log.info("Updating pet store with ID {}", petStoreData);
	 
	    // Call the savePet Store Method
	     return petStoreService.savePetStore(petStoreData); 
	}	
	
}
