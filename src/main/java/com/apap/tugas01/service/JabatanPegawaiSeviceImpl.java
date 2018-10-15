package com.apap.tugas01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apap.tugas01.repository.JabatanPegawaiDB;

@Service
@Transactional
public class JabatanPegawaiSeviceImpl implements JabatanPegawaiService{
	@Autowired
	JabatanPegawaiDB jabatanPegawaiDB;
	
}
