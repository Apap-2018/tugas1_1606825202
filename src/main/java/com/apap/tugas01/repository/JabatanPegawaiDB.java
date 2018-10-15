package com.apap.tugas01.repository;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.tugas01.model.JabatanPegawaiModel;

@Repository
public interface JabatanPegawaiDB extends JpaRepository<JabatanPegawaiModel, Long>{

}
