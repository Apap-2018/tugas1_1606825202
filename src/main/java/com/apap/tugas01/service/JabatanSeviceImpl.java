package com.apap.tugas01.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.repository.JabatanDB;

@Service
@Transactional
public class JabatanSeviceImpl implements JabatanService{
	@Autowired
	private JabatanDB jabatanDB;
	
	@Override
	public Set<String> getNamaJabatan() {
		// TODO Auto-generated method stub
		Set<String> namaJabatan = new HashSet<String>();
		for (JabatanModel jabatan : jabatanDB.findAll()) {
			namaJabatan.add(jabatan.getNama());
		}
		return namaJabatan;
	}
	
}
