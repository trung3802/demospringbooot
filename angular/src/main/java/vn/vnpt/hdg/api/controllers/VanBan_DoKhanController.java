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

import vn.vnpt.hdg.api.models.VanBan_DoKhan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VanBan_DoKhanService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vanBan_DoKhan")
public class VanBan_DoKhanController {
	
	@Autowired
	VanBan_DoKhanService sVanBan_DoKhan;
	
	@GetMapping("/getVanBan_DoKhans")
	public ResponseEntity<?> getVanBan_DoKhans(){
		return ResponseEntity.ok(new AppResponse(sVanBan_DoKhan.getVanBan_DoKhans()));
	}
	
	@PostMapping("/addVanBan_DoKhan")
	public ResponseEntity<?> addVanBan_DoKhan(@Valid @RequestBody VanBan_DoKhan vbdk) {
		return ResponseEntity.ok(sVanBan_DoKhan.addVanBan_DoKhan(vbdk));
	}
	
	@PostMapping("/editVanBan_DoKhan")
	public ResponseEntity<?> editVanBan_DoKhan(@Valid @RequestBody VanBan_DoKhan vbdk){
	    return ResponseEntity.ok(sVanBan_DoKhan.editVanBan_DoKhan(vbdk));
	}
	
	@DeleteMapping("/deleteVanBan_DoKhan/{id}")
	public ResponseEntity<?> deleteVanBan_DoKhan(@PathVariable Integer id) {
	    return ResponseEntity.ok(sVanBan_DoKhan.deleteVanBan_DoKhan(id));
	}
}
