package com.apap.tugas01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.repository.JabatanDB;

@Service
@Transactional
public class JabatanSeviceImpl implements JabatanService{
	@Autowired
	private JabatanDB jabatanDB;
	
	@Override
	public List<JabatanModel> getAllJabatan() {
		// TODO Auto-generated method stub
		return jabatanDB.findAll();
	}

	@Override
	public List<JabatanModel> getListJabatanById(List<String> listOfJabatanId) {
		// TODO Auto-generated method stub
		List<JabatanModel> listJabatan = null;
		
		for (String id:listOfJabatanId) {
			long ID = Long.parseLong(id);
			listJabatan.add(jabatanDB.findById(ID));
		}
		
		return listJabatan;
	}

	@Override
	public void add(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDB.save(jabatan);
	}

	@Override
	public JabatanModel getJabatanById(long id) {
		// TODO Auto-generated method stub
		return jabatanDB.findById(id);
	}

	@Override
	public void delete(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDB.delete(jabatan);
	}
	
}
