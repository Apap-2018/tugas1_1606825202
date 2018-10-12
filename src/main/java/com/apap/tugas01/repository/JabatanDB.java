package com.apap.tugas01.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas01.model.JabatanModel;

@Repository
public interface JabatanDB extends JpaRepository<JabatanModel, BigInteger>{

}
