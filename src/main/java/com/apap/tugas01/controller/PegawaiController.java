package com.apap.tugas01.controller;

import java.util.Collection;
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
	private String home() {
		return "home";
	}
	
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
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		//nip sementara
		pegawai.setNip("1234");
		//jabatan sementara
		pegawai.setJabatan(null);
		model.addAttribute("pegawai", pegawai);
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);

		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		
		InstansiModel instansi = new InstansiModel();
		model.addAttribute("instansi", instansi);
		pegawai.setInstansi(instansi);
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, @ModelAttribute InstansiModel instansi) {
		System.out.println(instansi.getNama());
		pegawaiService.add(pegawai);
		return "success";
	}
}
