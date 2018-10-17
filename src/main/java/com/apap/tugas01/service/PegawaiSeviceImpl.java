package com.apap.tugas01.service;

import java.util.Date;
import java.util.List;

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

	@Override
	public void add(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		pegawaiDB.save(pegawai);
	}

	@Override
	public String getUrutanPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		List<PegawaiModel> allPegawai = pegawaiDB.findAll();
		
		int indexPegawai = allPegawai.indexOf(pegawai);
		
		Date tanggalLahir = pegawai.getTanggalLahir();
		String tahunMasuk = pegawai.getTahunMasuk();
		int counter = 1;
		String counterStr = "";
		
		if (indexPegawai>0) {
			for (int i=0; i<indexPegawai; i++) {
				if (tanggalLahir == allPegawai.get(i).getTanggalLahir() &&
					tahunMasuk.equals(allPegawai.get(i).getTahunMasuk())){
					counter++;
				}
					
			}		
		}
		
		if (counter<10) {
			counterStr = "0" + counter;
		}
		
		return counterStr;
	}	
}
