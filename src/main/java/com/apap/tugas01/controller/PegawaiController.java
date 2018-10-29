package com.apap.tugas01.controller;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.JabatanModel;
//import com.apap.tugas01.model.JabatanPegawaiModel;
import com.apap.tugas01.model.PegawaiModel;
import com.apap.tugas01.model.ProvinsiModel;
import com.apap.tugas01.service.InstansiService;
import com.apap.tugas01.service.JabatanService;
//import com.apap.tugas01.service.JabatanPegawaiService;
import com.apap.tugas01.service.PegawaiService;
import com.apap.tugas01.service.ProvinsiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	@Autowired
	private ProvinsiService provinsiService;
	@Autowired
	private InstansiService instansiService;
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		model.addAttribute("listInstansi", listInstansi);
		return "home";
	}
	
	/**
	 * Fitur 1: Menampilkan Data Pegawai Berdasarkan NIP
	 */
	@RequestMapping("/pegawai")
	private String viewPegawaiByNIP(@RequestParam(value = "nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNIP(nip);
		List<JabatanModel> jabatanPegawai = pegawai.getJabatan();
		int gaji = (int)(jabatanPegawai.get(0).getGajiPokok() + 
				   (jabatanPegawai.get(0).getGajiPokok()*pegawai.getInstansi().getProvinsi().getPresentaseTunjangan()/100));
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatanPegawai", jabatanPegawai);
		model.addAttribute("gaji", gaji);
		return "view-pegawai";
	}
	
	/**
	 * Fitur 2: Menambahkan Data Pegawai di Suatu Instansi
	 */
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel(); 
		model.addAttribute("pegawai", pegawai);
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);

		InstansiModel instansi = new InstansiModel();
		model.addAttribute("instansi", instansi);
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		//4 digit kode instansi
		long kodeInstansi = pegawai.getInstansi().getId();
		System.out.println();
		
		//tanggal lahir 
		Date tanggalLahir = pegawai.getTanggalLahir();
		String pattern = "dd-MM-yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(tanggalLahir);
		date = date.replace("-", "");
		System.out.println(date);
		
		//tahun masuk
		String tahunMasuk = pegawai.getTahunMasuk();
		System.out.println(tahunMasuk);
		
		//nomor urutan pegawai
		String nomorUrutan = pegawaiService.getUrutanPegawai(pegawai);
		System.out.println(nomorUrutan);
		
		String nip = kodeInstansi + date + tahunMasuk + nomorUrutan;
		pegawai.setNip(nip);
		System.out.println(nip);
		
		System.out.println(pegawai.getId()
						   + "\n" + pegawai.getNama() 
						   + "\n" + pegawai.getNip()
						   + "\n" + pegawai.getTanggalLahir()
						   + "\n" + pegawai.getTempatLahir()
						   + "\n" + pegawai.getTahunMasuk()
						   + "\n" + pegawai.getInstansi()
						   + "\n" + pegawai.getJabatan());
		
		model.addAttribute("successMessage", "Data Pegawai berhasil ditambahkan!");
		pegawaiService.add(pegawai);
		return "success";
	}
	
	/**
	 * Fitur 3: Mengubah Data Pegawai
	 */
	@RequestMapping("/pegawai/ubah")
	private String updatePegawai(@RequestParam(value = "nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNIP(nip);
		model.addAttribute("pegawai", pegawai);
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		
		return "update-pegawai";
	}
	
	private String updatePegawaiSubmit(Model model) {
		model.addAttribute("successMessage", "Data Pegawai berhasil diubah!");
		
		return "success";
	}
	
	/**
	 * Fitur 4: Menampilkan Data Pegawai Berdasarkan Instansi, Provinsi, dan/atau Jabatan Tertentu
	 */
	@RequestMapping(value = "/pegawai/cari")
	private String viewPegawai(Model model) {
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		
		InstansiModel instansi = new InstansiModel();
		model.addAttribute("instansi", instansi);
		
		return "search-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/cari",  params = {"idProvinsi", "idInstansi", "idJabatan"})
	private String viewPegawaiSubmit(@RequestParam(value = "idProvinsi") long idProvinsi,
							   		 @RequestParam(value = "idInstansi") long idInstansi,
							   		 @RequestParam(value = "idJabatan") long idJabatan,
							   		 Model model) {

		ProvinsiModel provinsi = provinsiService.getProvinsiById(idProvinsi);
		InstansiModel instansi = instansiService.getInstansi(idInstansi);
		JabatanModel jabatan = jabatanService.getJabatanById(idJabatan);
		List<PegawaiModel> listPegawai = instansi.getPegawaiInstansi();
		List<PegawaiModel> listPegawaiFix = new ArrayList<>();
		for (PegawaiModel pegawai : listPegawai) {
			for (JabatanModel j : pegawai.getJabatan()) {
				if (j.equals(jabatan)) {
					listPegawaiFix.add(pegawai);
				}
			}
		}
		
		model.addAttribute("listPegawaiFix", listPegawaiFix);
		model.addAttribute("namaInstansi", instansi.getNama());
		model.addAttribute("namaJabatan", jabatan.getNama());
		model.addAttribute("provinsiSelected", provinsi);
		model.addAttribute("instansiSelected", instansi);
		model.addAttribute("jabatanSelected", jabatan);
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		
		InstansiModel instansi_ = new InstansiModel();
		model.addAttribute("instansi", instansi_);
		
		return "search-pegawai";
	}
	
	
	/**
	 * Fitur 10: Menampilkan Pegawai Termuda dan Tertua di Setiap Instansi
	 */
	@RequestMapping(value = "/pegawai/termuda-tertua")
	private String viewPegawaiTertuaTermuda(@RequestParam(value = "idInstansi") long idInstansi, Model model) {
		InstansiModel instansi = instansiService.getInstansi(idInstansi);
		
		PegawaiModel pegawaiTertua = instansi.getPegawaiInstansi().get(0);
		PegawaiModel pegawaiTermuda = instansi.getPegawaiInstansi().get(instansi.getPegawaiInstansi().size()-1);

		model.addAttribute("pegawaiTertua", pegawaiTertua);
		model.addAttribute("pegawaiTermuda", pegawaiTermuda);
		model.addAttribute("jabatanPegawaiTertua", instansi.getNama());
		model.addAttribute("jabatanPegawaiTermuda", instansi.getNama());

		return "view-pegawai-tertua-termuda";
	}

}
