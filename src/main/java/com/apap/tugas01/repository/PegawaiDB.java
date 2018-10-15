package com.apap.tugas01.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas01.model.PegawaiModel;

@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel, Long>{
	/**
	 * Method untuk mencari Pegawai berdasarkan NIP
	 */
	PegawaiModel findByNip(String nip);
	
}
