package com.apap.tugas01.service;

import java.util.Set;

import com.apap.tugas01.model.InstansiModel;

public interface InstansiService {
	Set<String> getNamaInstansi();
	
	InstansiModel getInstansi(long id);
}
