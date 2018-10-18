package com.apap.tugas01.service;

import java.util.List;
import com.apap.tugas01.model.JabatanModel;

public interface JabatanService {
	List<JabatanModel> getAllJabatan();
	List<JabatanModel> getListJabatanById(List<String> listOfJabatanId);
	void add(JabatanModel jabatan);
}
