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

import vn.vnpt.hdg.api.models.VaiTro_NguoiDung;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VaiTro_NguoiDungService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vaiTro_NguoiDung")
public class VaiTro_NguoiDungController {
	
	@Autowired
	VaiTro_NguoiDungService sVaiTro_NguoiDung;
	
	@GetMapping("/getVaiTro_NguoiDungs")
	public ResponseEntity<?> getVaiTroNguoiDungs(){
		return ResponseEntity.ok(new AppResponse(sVaiTro_NguoiDung.getVaiTro_NguoiDungs()));
	}
	
	@PostMapping("/addVaiTro_NguoiDung")
	public ResponseEntity<?> addVaiTro_NguoiDung(@Valid @RequestBody VaiTro_NguoiDung vtnd){
	    return ResponseEntity.ok(sVaiTro_NguoiDung.addVaiTro_NguoiDung(vtnd));
	}
	
	@PostMapping("/editVaiTro_NguoiDung")
	public ResponseEntity<?> editVaiTro_NguoiDung(@Valid @RequestBody VaiTro_NguoiDung vtnd){
	    return ResponseEntity.ok(sVaiTro_NguoiDung.editVaiTro_NguoiDung(vtnd));
	}
	
	@DeleteMapping("/deleteVaiTro_NguoiDung/{id}")
	public ResponseEntity<?> deleteVaiTro_NguoiDung(@PathVariable Integer id) {
	    return ResponseEntity.ok(sVaiTro_NguoiDung.deleteVaiTro_NguoiDung(id));
	}
}
