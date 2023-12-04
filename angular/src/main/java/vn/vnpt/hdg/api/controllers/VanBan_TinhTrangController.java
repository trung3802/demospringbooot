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

import vn.vnpt.hdg.api.models.VanBan_TinhTrang;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VanBan_TinhTrangService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vanBan_TinhTrang")
public class VanBan_TinhTrangController {
	
	@Autowired
	VanBan_TinhTrangService sVanBan_TinhTrang;
	
	@GetMapping("/getVanBan_TinhTrangs")
	public ResponseEntity<?> getVanBan_TinhTrangs(){
		return ResponseEntity.ok(new AppResponse(sVanBan_TinhTrang.getVanBan_TinhTrangs()));
	}
	
	@PostMapping("/addVanBan_TinhTrang")
	public ResponseEntity<?> addVanBan_TinhTrang(@Valid @RequestBody VanBan_TinhTrang vbtt) {
		return ResponseEntity.ok(sVanBan_TinhTrang.addVanBan_TinhTrang(vbtt));
	}
	
	@PostMapping("/editVanBan_TinhTrang")
	public ResponseEntity<?> editVanBan_TinhTrang(@Valid @RequestBody VanBan_TinhTrang vbtt){
	    return ResponseEntity.ok(sVanBan_TinhTrang.editVanBan_TinhTrang(vbtt));
	}
	
	@DeleteMapping("/deleteVanBan_TinhTrang/{id}")
	public ResponseEntity<?> deleteVanBan_TinhTrang(@PathVariable Integer id) {
	    return ResponseEntity.ok(sVanBan_TinhTrang.deleteVanBan_TinhTrang(id));
	}
}
