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
import vn.vnpt.hdg.api.services.LuuTruVanBanService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/luuTruVanBan")
public class LuuTruVanBanController {
	
	@Autowired
	LuuTruVanBanService sLuuTruVanBan;
	
	@GetMapping("/getVanBanDens")
	public ResponseEntity<?> getVanBanDens(){
		return ResponseEntity.ok(new AppResponse(sLuuTruVanBan.getVanBanDens()));
	}
	
	@GetMapping("/getVanBanDis")
	public ResponseEntity<?> getVanBanDis(){
		return ResponseEntity.ok(new AppResponse(sLuuTruVanBan.getVanBanDis()));
	}
	
	@PostMapping("/addVanBan")
	public ResponseEntity<?> addVanBan(@Valid @RequestBody VanBan vb) {
		return ResponseEntity.ok(sLuuTruVanBan.addVanBan(vb));
	}
	
	@PostMapping("/editVanBan")
	public ResponseEntity<?> editVanBan(@Valid @RequestBody VanBan cd){
	    return ResponseEntity.ok(sLuuTruVanBan.editVanBan(cd));
	}
	
	@DeleteMapping("/deleteVanBan/{id}")
	public ResponseEntity<?> deleteVanBan(@PathVariable Integer id) {
	    return ResponseEntity.ok(sLuuTruVanBan.deleteVanBan(id));
	}
}
