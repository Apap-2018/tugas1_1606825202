package com.apap.tugas01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.repository.InstansiDB;

@Service
@Transactional
public class InstansiSeviceImpl implements InstansiService{
	@Autowired
	private InstansiDB instansiDB;
	
	@Override
	public InstansiModel getInstansi(long id) {
		// TODO Auto-generated method stub
		return instansiDB.findById(id);
	}

	@Override
	public List<InstansiModel> getAllInstansi() {
		// TODO Auto-generated method stub
		return instansiDB.findAll();
	}
	
	
}
