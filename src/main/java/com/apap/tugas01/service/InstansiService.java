package com.apap.tugas01.service;

import java.util.List;
import java.util.Set;

import com.apap.tugas01.model.InstansiModel;

public interface InstansiService {
	InstansiModel getInstansi(long id);
	List<InstansiModel> getAllInstansi();
}
