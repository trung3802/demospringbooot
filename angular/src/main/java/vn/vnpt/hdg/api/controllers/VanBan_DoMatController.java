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

import vn.vnpt.hdg.api.models.VanBan_DoMat;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VanBan_DoMatService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vanBan_DoMat")
public class VanBan_DoMatController {
	
	@Autowired
	VanBan_DoMatService sVanBan_DoMat;
	
	@GetMapping("/getVanBan_DoMats")
	public ResponseEntity<?> getVanBan_DoMats(){
		return ResponseEntity.ok(new AppResponse(sVanBan_DoMat.getVanBan_DoMats()));
	}
	
	@PostMapping("/addVanBan_DoMat")
	public ResponseEntity<?> addVanBan_DoMat(@Valid @RequestBody VanBan_DoMat vbdm) {
		return ResponseEntity.ok(sVanBan_DoMat.addVanBan_DoMat(vbdm));
	}
	
	@PostMapping("/editVanBan_DoMat")
	public ResponseEntity<?> editVanBan_DoMat(@Valid @RequestBody VanBan_DoMat vbdm){
	    return ResponseEntity.ok(sVanBan_DoMat.editVanBan_DoMat(vbdm));
	}
	
	@DeleteMapping("/deleteVanBan_DoMat/{id}")
	public ResponseEntity<?> deleteVanBan_DoMat(@PathVariable Integer id) {
	    return ResponseEntity.ok(sVanBan_DoMat.deleteVanBan_DoMat(id));
	}
}
