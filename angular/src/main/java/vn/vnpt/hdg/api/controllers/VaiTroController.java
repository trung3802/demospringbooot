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

import vn.vnpt.hdg.api.models.VaiTro;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.VaiTroService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vaiTro")
public class VaiTroController {
	
	@Autowired
	VaiTroService sVaiTro;
	
	@GetMapping("/getVaiTros")
	public ResponseEntity<?> getVaiTros(){
		return ResponseEntity.ok(new AppResponse(sVaiTro.getVaiTros()));
	}
	
	@PostMapping("/addVaiTro")
	public ResponseEntity<?> addVaiTro(@Valid @RequestBody VaiTro vt) {
		return ResponseEntity.ok(sVaiTro.addVaiTro(vt));
	}
	
	@PostMapping("/editVaiTro")
	public ResponseEntity<?> editVaiTro(@Valid @RequestBody VaiTro vt){
	    return ResponseEntity.ok(sVaiTro.editVaiTro(vt));
	}
	
	@DeleteMapping("/deleteVaiTro/{id}")
	public ResponseEntity<?> deleteVaiTro(@PathVariable Integer id) {
	    return ResponseEntity.ok(sVaiTro.deleteVaiTro(id));
	}
}
