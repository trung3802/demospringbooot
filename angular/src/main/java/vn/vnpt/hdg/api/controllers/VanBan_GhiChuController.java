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

import vn.vnpt.hdg.api.models.VanBan_GhiChu;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VanBan_GhiChuService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vanBan_GhiChu")
public class VanBan_GhiChuController {
	
	@Autowired
	VanBan_GhiChuService sVanBan_GhiChu;
	
	@GetMapping("/getVanBan_GhiChus")
	public ResponseEntity<?> getVanBan_GhiChus(){
		return ResponseEntity.ok(new AppResponse(sVanBan_GhiChu.getVanBan_GhiChus()));
	}
	
	@PostMapping("/addVanBan_GhiChu")
	public ResponseEntity<?> addVanBan_GhiChu(@Valid @RequestBody VanBan_GhiChu vbgc) {
		return ResponseEntity.ok(sVanBan_GhiChu.addVanBan_GhiChu(vbgc));
	}
	
	@PostMapping("/editVanBan_GhiChu")
	public ResponseEntity<?> editVanBan_GhiChu(@Valid @RequestBody VanBan_GhiChu vbgc){
	    return ResponseEntity.ok(sVanBan_GhiChu.editVanBan_GhiChu(vbgc));
	}
	
	@DeleteMapping("/deleteVanBan_GhiChu/{id}")
	public ResponseEntity<?> deleteVanBan_GhiChu(@PathVariable Integer id) {
	    return ResponseEntity.ok(sVanBan_GhiChu.deleteVanBan_GhiChu(id));
	}
}
