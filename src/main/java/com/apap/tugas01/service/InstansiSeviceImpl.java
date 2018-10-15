package com.apap.tugas01.service;

import java.util.HashSet;
import java.util.Set;

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
	
	/**
	 * Fungsi untuk mengembalikan Set yang berisi nama2 instansi (tanpa duplikat)
	 */
	public Set<String> getNamaInstansi(){
		Set<String> namaInstansi = new HashSet<String>();
		for (InstansiModel instansi : instansiDB.findAll()) {
			namaInstansi.add(instansi.getNama());
		}
		return namaInstansi;
	}

	@Override
	public InstansiModel getInstansi(long id) {
		// TODO Auto-generated method stub
		return instansiDB.findById(id);
	}
	
	
}
