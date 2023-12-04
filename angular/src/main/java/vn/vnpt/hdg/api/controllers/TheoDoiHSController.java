package vn.vnpt.hdg.api.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.HoSoRequest;
import vn.vnpt.hdg.api.services.TheoDoiHSService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/theoDoiHS")
public class TheoDoiHSController {
	
	@Autowired
	TheoDoiHSService sTheoDoiHS;
	
	@PostMapping("/getTheoDoiHS")
	@Transactional
    public ResponseEntity<?> getTheoDoiHS(@Valid @RequestBody HoSoRequest tdhs) {
		return ResponseEntity.ok(new AppResponse(sTheoDoiHS.getTheoDoiHS(tdhs)));
    }
}