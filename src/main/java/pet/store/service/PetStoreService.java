package pet.store.service;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {

	@Autowired
	private PetStoreDao petStoreDao;
    
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {

		// Find or create a new object based on the ID.
		Long petStoreId = petStoreData.getPetStoreId();

		// This will either be an empty object or the one that exists in the database
		PetStore petStore = findOrCreatePetStore(petStoreId);

		// Next copy the fields from the data object being passed in
		// to the dao object
		copyPetStoreData(petStoreData, petStore);

		// Save the updated petStore fields
		PetStore saved = petStoreDao.save(petStore);

		// Create a new updated object that will have the NEW values
		// set into it to return to the user.
		PetStoreData updated = new PetStoreData();

		// Copy the values returned from the save operation into a new
		// object that has the updated information
		copyPetStoreFields(saved, updated);

		// Return the updated information
		return updated;
	}

	// Copies properties from data into store updating the rhs object
	private void copyPetStoreData(PetStoreData petStoreData, PetStore petStore) {
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreId(petStoreData.getPetStoreId());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());

	}

	// Copies properties from store into data updating the rhs object
	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		// copy field from store into the data object the other way...
		petStoreData.setPetStoreName(petStore.getPetStoreName());
		petStoreData.setPetStoreId(petStore.getPetStoreId());
		petStoreData.setPetStoreAddress(petStore.getPetStoreAddress());
		petStoreData.setPetStoreCity(petStore.getPetStoreCity());
		petStoreData.setPetStoreState(petStore.getPetStoreState());
		petStoreData.setPetStoreZip(petStore.getPetStoreZip());
		petStoreData.setPetStorePhone(petStore.getPetStorePhone());

	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore petStore;

		if (Objects.isNull(petStoreId)) {
			petStore = new PetStore();
		} else {
			petStore = petStoreDao.findById(petStoreId)
					.orElseThrow(() -> new NoSuchElementException("PetStore with Id =" + petStoreId + "was not found"));
		}

		return petStore;

	}

	@Transactional(readOnly = true)
	public List<PetStoreData> retrieveAllPetStores() {
		List<PetStore> petStores = petStoreDao.findAll();
		List<PetStoreData> response = new LinkedList<>(); 
		
		for(PetStore petstore : petStores) {
			response.add(new PetStoreData(petstore)); 
		}
		
		return response;
	}

}
