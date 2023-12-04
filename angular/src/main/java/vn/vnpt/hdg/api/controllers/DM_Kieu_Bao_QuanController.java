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

import vn.vnpt.hdg.api.models.DM_Kieu_Bao_Quan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.DM_Kieu_Bao_QuanService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dm_Kieu_Bao_Quan")
public class DM_Kieu_Bao_QuanController {
	
	@Autowired
	DM_Kieu_Bao_QuanService sDM_Kieu_Bao_Quan;
	
	@GetMapping("/getDM_Kieu_Bao_Quans")
	public ResponseEntity<?> getDM_Kieu_Bao_Quans(){
		return ResponseEntity.ok(new AppResponse(sDM_Kieu_Bao_Quan.getDM_Kieu_Bao_Quans()));
	}
	
	@PostMapping("/addDM_Kieu_Bao_Quan")
	public ResponseEntity<?> addDM_Kieu_Bao_Quan(@Valid @RequestBody DM_Kieu_Bao_Quan dmkbq) {
		return ResponseEntity.ok(sDM_Kieu_Bao_Quan.addDM_Kieu_Bao_Quan(dmkbq));
	}
	
	@PostMapping("/editDM_Kieu_Bao_Quan")
	public ResponseEntity<?> editDM_Kieu_Bao_Quan(@Valid @RequestBody DM_Kieu_Bao_Quan dmkbq){
	    return ResponseEntity.ok(sDM_Kieu_Bao_Quan.editDM_Kieu_Bao_Quan(dmkbq));
	}
	
	@DeleteMapping("/deleteDM_Kieu_Bao_Quan/{id}")
	public ResponseEntity<?> deleteDM_Kieu_Bao_Quan(@PathVariable Integer id) {
	    return ResponseEntity.ok(sDM_Kieu_Bao_Quan.deleteDM_Kieu_Bao_Quan(id));
	}
}
