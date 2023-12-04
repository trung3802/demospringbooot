package vn.vnpt.hdg.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.vnpt.hdg.api.models.VanBan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.TraCuuVanBanService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/traCuuVanBan")
public class TraCuuVanBanController {
	
	@Autowired
	TraCuuVanBanService sTraCuuVanBan;
	
	@PostMapping("/getVanBanToanDonVis")
	public ResponseEntity<?> getVanBanToanDonVis(@Valid @RequestBody VanBan vb){
		return ResponseEntity.ok(new AppResponse(sTraCuuVanBan.getVanBanToanDonVis(vb)));
	}
}