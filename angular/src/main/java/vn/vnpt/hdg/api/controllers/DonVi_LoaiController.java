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

import vn.vnpt.hdg.api.models.DonVi_Loai;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.DonVi_LoaiService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/donVi_Loai")
public class DonVi_LoaiController {

	@Autowired
	DonVi_LoaiService sDonVi_Loai;
	
	@GetMapping("/getDonVi_Loais")
	public ResponseEntity<?> getDonVi_Loais(){
		return ResponseEntity.ok(new AppResponse(sDonVi_Loai.getDonVi_Loais()));
	}
	
	@PostMapping("/addDonVi_Loai")
	public ResponseEntity<?> addDonVi_Loai(@Valid @RequestBody DonVi_Loai dvl) {
		return ResponseEntity.ok(sDonVi_Loai.addDonVi_Loai(dvl));
	}
	
	@PostMapping("/editDonVi_Loai")
	public ResponseEntity<?> editDonVi_Loai(@Valid @RequestBody DonVi_Loai dvl){
	    return ResponseEntity.ok(sDonVi_Loai.editDonVi_Loai(dvl));
	}
	
	@DeleteMapping("/deleteDonVi_Loai/{id}")
	public ResponseEntity<?> deleteDonVi_Loai(@PathVariable Integer id) {
	    return ResponseEntity.ok(sDonVi_Loai.deleteDonVi_Loai(id));
	}
}
