
// Takes data from the controller that handles the bussinnes layer and then passes the data to the DAO object. 


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
		copyFromDtoToDao(petStoreData, petStore);

		// Save the updated petStore fields
		PetStore saved = petStoreDao.save(petStore);

		// Create a new updated object that will have the NEW values
		// set into it to return to the user.
		PetStoreData updated = new PetStoreData();

		// Copy the values returned from the save operation into a new
		// object that has the updated information
		copyFromDaoToDto(saved, updated);

		// Return the updated information
		return updated;
	}

	// Copies properties from data into store updating the rhs object
	private void copyFromDtoToDao(PetStoreData pDto, PetStore pDao) {
		pDao.setPetStoreName(pDto.getPetStoreName());
		pDao.setPetStoreId(pDto.getPetStoreId());
		pDao.setPetStoreAddress(pDto.getPetStoreAddress());
		pDao.setPetStoreCity(pDto.getPetStoreCity());
		pDao.setPetStoreState(pDto.getPetStoreState());
		pDao.setPetStoreZip(pDto.getPetStoreZip());
		pDao.setPetStorePhone(pDto.getPetStorePhone());

	}

	// Copies properties from store into data updating the rhs object
	private void copyFromDaoToDto(PetStore pDao, PetStoreData pDto) {
		// copy field from store into the data object the other way...
		pDto.setPetStoreName(pDao.getPetStoreName());
		pDto.setPetStoreId(pDao.getPetStoreId());
		pDto.setPetStoreAddress(pDao.getPetStoreAddress());
		pDto.setPetStoreCity(pDao.getPetStoreCity());
		pDto.setPetStoreState(pDao.getPetStoreState());
		pDto.setPetStoreZip(pDao.getPetStoreZip());
		pDto.setPetStorePhone(pDao.getPetStorePhone());

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

}
