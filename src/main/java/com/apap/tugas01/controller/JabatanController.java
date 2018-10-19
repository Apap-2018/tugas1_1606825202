package com.apap.tugas01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.service.JabatanService;

@Controller
public class JabatanController {
	@Autowired
	JabatanService jabatanService;
	
	@RequestMapping(value = "/get/list-jabatan", method = RequestMethod.GET)
	public @ResponseBody List<JabatanModel> getInstansiById(@RequestParam(value = "listOfJabatanId", required = true) List<String> listOfJabatanId) {
	    List<JabatanModel> listJabatan = jabatanService.getListJabatanById(listOfJabatanId);
	    return listJabatan; 
	}
	
	/**
	 * Fitur 5: Menambahkan Jabatan
	 */
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	public String addJabatan(Model model) {
		JabatanModel jabatan = new JabatanModel();
		
		model.addAttribute("jabatan", jabatan);
		return "add-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	public String addJabatanSubmit(@ModelAttribute JabatanModel jabatan) {
		jabatanService.add(jabatan);
		return "success";
	}
	
	/**
	 * Fitur 6: Menampilkan Data Suatu Jabatan
	 */
	@RequestMapping(value = "/jabatan/view")
	public String viewJabatan(@RequestParam (value = "idJabatan") long idJabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(idJabatan);
		String deleteStatus;
		if (jabatan.countPegawai() == 0) {
			deleteStatus = "allowed";
		}
		else {
			deleteStatus = "forbidden";
		}
		model.addAttribute("deleteStatus", deleteStatus);
		model.addAttribute("jabatan", jabatan);
		return "view-jabatan";
	}
	
	/**
	 * Fitur 8: Menghapus Jabatan
	 */
	@RequestMapping(value = "/jabatan/hapus")
	public String deleteJabatan(@RequestParam(value = "idJabatan") long idJabatan) {
		jabatanService.delete(jabatanService.getJabatanById(idJabatan));
		return "success";
	}
	
	/**
	 * Fitur 7: Mengubah Data Jabatan
	 */
	@RequestMapping(value = "jabatan/ubah", method = RequestMethod.GET)
	public String updateJabatan(@RequestParam(value = "idJabatan") long idJabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(idJabatan);
		model.addAttribute("jabatan", jabatan);
		return "update-jabatan";
	}
	
	@RequestMapping(value = "jabatan/ubah", method = RequestMethod.POST)
	public String updateJabatanSubmit(@ModelAttribute JabatanModel jabatan){
		jabatanService.add(jabatan);
		return "success";
	}
	
	/**
	 * Fitur 9: Menampilkan Daftar Jabatan
	 * 
	 */
	@RequestMapping(value = "jabatan/viewall")
	public String viewAll(Model model) {
		model.addAttribute("listJabatan", jabatanService.getAllJabatan());
		return "view-all-jabatan";
	}
}
