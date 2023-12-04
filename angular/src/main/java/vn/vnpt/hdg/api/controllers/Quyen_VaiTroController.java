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

import vn.vnpt.hdg.api.models.Quyen_VaiTro;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.Quyen_VaiTroService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/quyen_VaiTro")
public class Quyen_VaiTroController {
	
	@Autowired
	Quyen_VaiTroService sQuyen_VaiTro;
	
	@GetMapping("/getQuyen_VaiTros")
	public ResponseEntity<?> getVaiTroNguoiDungs(){
		return ResponseEntity.ok(new AppResponse(sQuyen_VaiTro.getQuyen_VaiTros()));
	}
	
	@PostMapping("/addQuyen_VaiTro")
	public ResponseEntity<?> addQuyen_VaiTro(@Valid @RequestBody Quyen_VaiTro qtv){
	    return ResponseEntity.ok(sQuyen_VaiTro.addQuyen_VaiTro(qtv));
	}
	
	@PostMapping("/editQuyen_VaiTro")
	public ResponseEntity<?> editQuyen_VaiTro(@Valid @RequestBody Quyen_VaiTro qtv){
	    return ResponseEntity.ok(sQuyen_VaiTro.editQuyen_VaiTro(qtv));
	}
	
	@DeleteMapping("/deleteQuyen_VaiTro/{id}")
	public ResponseEntity<?> deleteQuyen_VaiTro(@PathVariable Integer id) {
	    return ResponseEntity.ok(sQuyen_VaiTro.deleteQuyen_VaiTro(id));
	}
}
