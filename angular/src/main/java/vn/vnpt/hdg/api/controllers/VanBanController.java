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

import vn.vnpt.hdg.api.models.VanBan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VanBanService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vanBan")
public class VanBanController {
		
	@Autowired
	VanBanService VanBan;
	
	@GetMapping("/getVanBans")
	public ResponseEntity<?> getVanBan(){
		return ResponseEntity.ok(new AppResponse(VanBan.getVanBans()));
	}
	@PostMapping("/addVanBan")
	public ResponseEntity<?> addVanBan(@Valid @RequestBody VanBan vb) {
		return ResponseEntity.ok(VanBan.addVanBan(vb));
	}
	
	@PostMapping("/editVanBan")
	public ResponseEntity<?> editVanBan(@Valid @RequestBody VanBan vb){
	    return ResponseEntity.ok(VanBan.editVanBan(vb));
	}
	@DeleteMapping("/deleteVanBan/{id}")
	public ResponseEntity<?> deleteVanBan(@PathVariable Integer id) {
	    return ResponseEntity.ok(VanBan.deleteVanBan(id));
	}
}
