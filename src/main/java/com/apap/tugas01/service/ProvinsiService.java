package com.apap.tugas01.service;

import java.math.BigInteger;
import java.util.List;
import com.apap.tugas01.model.ProvinsiModel;;


public interface ProvinsiService {
	List<ProvinsiModel> getAll();
	
	void addProvinsi(ProvinsiModel provinsi);
	
	ProvinsiModel getProvinsiById(long provinsiId);
}
