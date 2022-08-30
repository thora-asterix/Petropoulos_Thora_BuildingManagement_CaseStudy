package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;

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
}
