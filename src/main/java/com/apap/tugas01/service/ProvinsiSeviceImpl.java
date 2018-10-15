package com.apap.tugas01.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas01.model.ProvinsiModel;
import com.apap.tugas01.repository.ProvinsiDB;

@Service
@Transactional
public class ProvinsiSeviceImpl implements ProvinsiService {
	@Autowired
	private ProvinsiDB provinsiDB;
	
	public List<ProvinsiModel> getAll() {
		return provinsiDB.findAll();
	}
	
	public void addProvinsi(ProvinsiModel provinsi) {
		provinsiDB.save(provinsi);
	}

	@Override
	public ProvinsiModel getProvinsiById(long id) {
		// TODO Auto-generated method stub
		return provinsiDB.findById(id);
	}
}
