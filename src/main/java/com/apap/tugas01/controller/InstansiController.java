package com.apap.tugas01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.service.InstansiService;

@Controller
public class InstansiController {
	@Autowired
	InstansiService instansiService;
	
	@RequestMapping(value = "/get/instansi", method = RequestMethod.GET)
	public @ResponseBody InstansiModel getInstansiById(@RequestParam(value = "instansiId", required = true) long instansiId) {
	    InstansiModel instansi = instansiService.getInstansi(instansiId);
	    return instansi; 
	}
}
