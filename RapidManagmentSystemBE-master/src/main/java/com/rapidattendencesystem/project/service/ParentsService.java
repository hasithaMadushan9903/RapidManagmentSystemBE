package com.rapidattendencesystem.project.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rapidattendencesystem.project.dto.ParentDTO;
import com.rapidattendencesystem.project.entity.Parent;
import com.rapidattendencesystem.project.repo.ParentRepo;

@Transactional
@Service
public class ParentsService {
	
	@Autowired
	private ParentRepo parentrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	public List<ParentDTO> getallParent() {
		
		return modelmapper.map(parentrepo.findAll(), new TypeToken<List<ParentDTO>>() {}.getType()) ;
	}
	
	public int addParent(ParentDTO parentdao) {
		try {
			parentrepo.save(modelmapper.map(parentdao, Parent.class));
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public ParentDTO getParentById(String nic) {
		Parent parent = parentrepo.findByNic(nic);
		if(parent == null) {
			return null;
		}else {
			return modelmapper.map(parentrepo.findByNic(nic), ParentDTO.class) ;
		}
	}
}
