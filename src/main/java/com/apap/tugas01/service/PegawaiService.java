package com.apap.tugas01.service;

import com.apap.tugas01.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getPegawaiByNIP(String NIP);
	void add(PegawaiModel pegawai);
}
