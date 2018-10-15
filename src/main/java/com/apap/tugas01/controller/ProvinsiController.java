package com.apap.tugas01.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.ProvinsiModel;
import com.apap.tugas01.service.ProvinsiService;

@Controller
public class ProvinsiController {
	@Autowired
	private ProvinsiService provinsiService;
	
	@RequestMapping(value = "/provinsi/tambah",  method=RequestMethod.GET)
	public String addProvinsi(Model model) {
		model.addAttribute("provinsi", new ProvinsiModel());
		return "add-provinsi";
	}
	
	@RequestMapping(value = "/provinsi/tambah", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute ProvinsiModel provinsi) {
		provinsiService.addProvinsi(provinsi);
		return "add";
	}
	
	@RequestMapping(value = "/provinsi/instansi", method = RequestMethod.GET)
	public @ResponseBody List<InstansiModel> findAllInstansi(@RequestParam(value = "provinsiId", required = true) long provinsiId) {
	    ProvinsiModel provinsi = provinsiService.getProvinsiById(provinsiId);
	    for (InstansiModel instansi:provinsi.getInstansiProvinsi()) {
	    	System.out.println(instansi.getNama());
	    }
	    return provinsi.getInstansiProvinsi(); 
	}
	
}
