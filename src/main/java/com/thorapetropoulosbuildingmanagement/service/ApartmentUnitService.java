package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;
import com.thorapetropoulosbuildingmanagement.model.Issue;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.repository.ApartmentUnitRepository;

@Service
public class ApartmentUnitService {

	@Autowired
	private ApartmentUnitRepository apartmentUnitRepository;
	// get all apartment units
		public List<ApartmentUnit> findAll(){
			return apartmentUnitRepository.findAll();
		}
		
		// get apartment units by id
		public ApartmentUnit findById(Integer id) {
			return apartmentUnitRepository.findById(id).get();
		}
		
		// get apartment unit by apartment number
		public ApartmentUnit findByApartmentUnitNumber(Integer num) {
			return apartmentUnitRepository.findByApartmentUnitNumber(num);
		}
		
		// delete apartment units
		public void deleteById(Integer id) {
			apartmentUnitRepository.deleteById(id);
		}
		
		// save/update apartment units
		public void save(ApartmentUnit apartmentUnit) {
			apartmentUnitRepository.save(apartmentUnit);
		}
		
		public List<ApartmentUnit> findAllByRentalStatus(String st){
			
			return apartmentUnitRepository.findAllByRentalStatus(st);
			
		}
		
		public Page<ApartmentUnit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection ){
			
			Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
				Sort.by(sortField).descending();
			
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
			
			return apartmentUnitRepository.findAll(pageable);	
		}
		
}
