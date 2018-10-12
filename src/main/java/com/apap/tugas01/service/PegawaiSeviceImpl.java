package com.apap.tugas01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apap.tugas01.model.PegawaiModel;
import com.apap.tugas01.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiSeviceImpl implements PegawaiService{
	@Autowired
	private PegawaiDB pegawaiDB;

	@Override
	public PegawaiModel getPegawaiByNIP(String NIP) {
		// TODO Auto-generated method stub
		return pegawaiDB.findByNip(NIP);
	}
}
