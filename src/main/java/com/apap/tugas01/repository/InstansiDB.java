package com.apap.tugas01.repository;

import java.math.BigInteger;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas01.model.InstansiModel;

@Repository
public interface InstansiDB extends JpaRepository<InstansiModel, Long>{
	InstansiModel findById(long id);
}
