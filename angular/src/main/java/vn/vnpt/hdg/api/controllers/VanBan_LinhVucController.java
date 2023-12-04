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

import vn.vnpt.hdg.api.models.VanBan_LinhVuc;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VanBan_LinhVucService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vanBan_LinhVuc")
public class VanBan_LinhVucController {
	
	@Autowired
	VanBan_LinhVucService sVanBan_LinhVuc;
	
	@GetMapping("/getVanBan_LinhVucs")
	public ResponseEntity<?> getVanBan_LinhVucs(){
		return ResponseEntity.ok(new AppResponse(sVanBan_LinhVuc.getVanBan_LinhVucs()));
	}
	
	@PostMapping("/addVanBan_LinhVuc")
	public ResponseEntity<?> addVanBan_LinhVuc(@Valid @RequestBody VanBan_LinhVuc vblv) {
		return ResponseEntity.ok(sVanBan_LinhVuc.addVanBan_LinhVuc(vblv));
	}
	
	@PostMapping("/editVanBan_LinhVuc")
	public ResponseEntity<?> editVanBan_LinhVuc(@Valid @RequestBody VanBan_LinhVuc vblv){
	    return ResponseEntity.ok(sVanBan_LinhVuc.editVanBan_LinhVuc(vblv));
	}
	
	@DeleteMapping("/deleteVanBan_LinhVuc/{id}")
	public ResponseEntity<?> deleteVanBan_LinhVuc(@PathVariable Integer id) {
	    return ResponseEntity.ok(sVanBan_LinhVuc.deleteVanBan_LinhVuc(id));
	}
}
