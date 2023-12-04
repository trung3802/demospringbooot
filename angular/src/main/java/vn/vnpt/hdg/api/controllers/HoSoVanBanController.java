package vn.vnpt.hdg.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.vnpt.hdg.api.models.Ho_So_VB;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.HoSoVanBanService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/hoSoVanBan")
public class HoSoVanBanController {
	
	@Autowired
	HoSoVanBanService sHoSoVanBan;
	
	@PostMapping("/getVanBanByHoSos")
	public ResponseEntity<?> getVanBanByHoSos(@Valid @RequestBody Ho_So_VB hsvb){
		return ResponseEntity.ok(new AppResponse(sHoSoVanBan.getVanBanByHoSos(hsvb)));
	}
	
	@PostMapping("/addHoSoVanBan")
	public ResponseEntity<?> addHoSoVanBan(@Valid @RequestBody Ho_So_VB hsvb) {
		return ResponseEntity.ok(sHoSoVanBan.addHoSoVanBan(hsvb));
	}
	
	@PostMapping("/editHoSoVanBan")
	public ResponseEntity<?> editHoSoVanBan(@Valid @RequestBody Ho_So_VB hsvb){
	    return ResponseEntity.ok(sHoSoVanBan.editHoSoVanBan(hsvb));
	}
	
	@DeleteMapping("/deleteHoSoVanBan/{id}")
	public ResponseEntity<?> deleteHoSoVanBan(@PathVariable Integer id) {
	    return ResponseEntity.ok(sHoSoVanBan.deleteHoSoVanBan(id));
	}
}