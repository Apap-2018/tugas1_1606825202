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
	
}
