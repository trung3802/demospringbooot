package vn.vnpt.hdg.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.NguoiDungService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/nguoiDung")
public class NguoiDungController {
	
	@Autowired
	NguoiDungService sNguoiDung;
	
	@GetMapping("/getNguoiDungs")
	public ResponseEntity<?> getNguoiDungs(){
		return ResponseEntity.ok(new AppResponse(sNguoiDung.getNguoiDungs()));
	}
	
	@PostMapping("/getNguoiDungByDonVis/{id}")
	public ResponseEntity<?> getNguoiDungByDonVis(@PathVariable Integer id){
		return ResponseEntity.ok(sNguoiDung.getNguoiDungByDonVis(id));
	}
	
	@PostMapping("/addNguoiDung")
	public ResponseEntity<?> addNguoiDung(@Valid @RequestBody NguoiDung nd) {
		return ResponseEntity.ok(sNguoiDung.addNguoiDung(nd));
	}
	
	@PostMapping("/editNguoiDung")
	public ResponseEntity<?> editNguoiDung(@Valid @RequestBody NguoiDung nd){
	    return ResponseEntity.ok(sNguoiDung.editNguoiDung(nd));
	}
	
	@DeleteMapping("/deleteNguoiDung/{id}")
	public ResponseEntity<?> deleteNguoiDung(@PathVariable Integer id) {
	    return ResponseEntity.ok(sNguoiDung.deleteNguoiDung(id));
	}
}
