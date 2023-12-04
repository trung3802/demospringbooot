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

import vn.vnpt.hdg.api.models.VanBan_Kieu;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VanBan_KieuService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vanBan_Kieu")
public class VanBan_KieuController {
	
	@Autowired
	VanBan_KieuService sVanBan_Kieu;
	
	@GetMapping("/getVanBan_Kieus")
	public ResponseEntity<?> getVanBan_Kieus(){
		return ResponseEntity.ok(new AppResponse(sVanBan_Kieu.getVanBan_Kieus()));
	}
	
	@PostMapping("/addVanBan_Kieu")
	public ResponseEntity<?> addVanBan_Kieu(@Valid @RequestBody VanBan_Kieu vbk) {
		return ResponseEntity.ok(sVanBan_Kieu.addVanBan_Kieu(vbk));
	}
	
	@PostMapping("/editVanBan_Kieu")
	public ResponseEntity<?> editVanBan_Kieu(@Valid @RequestBody VanBan_Kieu vbk){
	    return ResponseEntity.ok(sVanBan_Kieu.editVanBan_Kieu(vbk));
	}
	
	@DeleteMapping("/deleteVanBan_Kieu/{id}")
	public ResponseEntity<?> deleteVanBan_Kieu(@PathVariable Integer id) {
	    return ResponseEntity.ok(sVanBan_Kieu.deleteVanBan_Kieu(id));
	}
}
