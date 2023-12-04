package vn.vnpt.hdg.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.DonViNguoiDungService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/donViNguoiDung")
public class DonViNguoiDungController {
	
	@Autowired
	DonViNguoiDungService sDonViNguoiDung;
	
	@PostMapping("/getDonViByNguoiDung")
	public ResponseEntity<?> getDonViByNguoiDung(@Valid @RequestBody NguoiDung nd){
		return ResponseEntity.ok(new AppResponse(sDonViNguoiDung.getDonViByNguoiDung(nd)));
	}
}