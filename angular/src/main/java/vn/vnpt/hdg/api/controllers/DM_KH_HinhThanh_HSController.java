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

import vn.vnpt.hdg.api.models.DM_KH_HinhThanh_HS;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.DM_KH_HinhThanh_HSService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dm_KH_HinhThanh_HS")
public class DM_KH_HinhThanh_HSController {
	
	@Autowired
	DM_KH_HinhThanh_HSService sDM_KH_HinhThanh_HS;
	
	@GetMapping("/getDM_KH_HinhThanh_HSs")
	public ResponseEntity<?> getDM_KH_HinhThanh_HSs(){
		return ResponseEntity.ok(new AppResponse(sDM_KH_HinhThanh_HS.getDM_KH_HinhThanh_HSs()));
	}
	
	@PostMapping("/addDM_KH_HinhThanh_HS")
	public ResponseEntity<?> addDM_KH_HinhThanh_HS(@Valid @RequestBody DM_KH_HinhThanh_HS dmkhthhs) {
		return ResponseEntity.ok(sDM_KH_HinhThanh_HS.addDM_KH_HinhThanh_HS(dmkhthhs));
	}
	
	@PostMapping("/editDM_KH_HinhThanh_HS")
	public ResponseEntity<?> editDM_KH_HinhThanh_HS(@Valid @RequestBody DM_KH_HinhThanh_HS dmkhthhs){
	    return ResponseEntity.ok(sDM_KH_HinhThanh_HS.editDM_KH_HinhThanh_HS(dmkhthhs));
	}
	
	@DeleteMapping("/deleteDM_KH_HinhThanh_HS/{id}")
	public ResponseEntity<?> deleteDM_KH_HinhThanh_HS(@PathVariable Integer id) {
	    return ResponseEntity.ok(sDM_KH_HinhThanh_HS.deleteDM_KH_HinhThanh_HS(id));
	}
}
