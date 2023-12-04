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

import vn.vnpt.hdg.api.models.VanBan_Loai;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VanBan_LoaiService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vanBan_Loai")
public class VanBan_LoaiController {
	
	@Autowired
	VanBan_LoaiService sVanBan_Loai;
	
	@GetMapping("/getVanBan_Loais")
	public ResponseEntity<?> getVanBan_Loais(){
		return ResponseEntity.ok(new AppResponse(sVanBan_Loai.getVanBan_Loais()));
	}
	
	@PostMapping("/addVanBan_Loai")
	public ResponseEntity<?> addVanBan_Loai(@Valid @RequestBody VanBan_Loai vbl) {
		return ResponseEntity.ok(sVanBan_Loai.addVanBan_Loai(vbl));
	}
	
	@PostMapping("/editVanBan_Loai")
	public ResponseEntity<?> editVanBan_Loai(@Valid @RequestBody VanBan_Loai vbl){
	    return ResponseEntity.ok(sVanBan_Loai.editVanBan_Loai(vbl));
	}
	
	@DeleteMapping("/deleteVanBan_Loai/{id}")
	public ResponseEntity<?> deleteVanBan_Loai(@PathVariable Integer id) {
	    return ResponseEntity.ok(sVanBan_Loai.deleteVanBan_Loai(id));
	}
}
