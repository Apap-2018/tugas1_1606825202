package com.apap.tugas01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
