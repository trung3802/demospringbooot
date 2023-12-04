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

import vn.vnpt.hdg.api.models.ChucDanh;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.ChucDanhService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/chucDanh")
public class ChucDanhController {
	
	@Autowired
	ChucDanhService sChucDanh;
	
	@GetMapping("/getChucDanhs")
	public ResponseEntity<?> getChucDanhs(){
		return ResponseEntity.ok(new AppResponse(sChucDanh.getChucDanhs()));
	}
	
	@PostMapping("/addChucDanh")
	public ResponseEntity<?> addChucDanh(@Valid @RequestBody ChucDanh cd) {
		return ResponseEntity.ok(sChucDanh.addChucDanh(cd));
	}
	
	@PostMapping("/editChucDanh")
	public ResponseEntity<?> editChucDanh(@Valid @RequestBody ChucDanh cd){
	    return ResponseEntity.ok(sChucDanh.editChucDanh(cd));
	}
	
	@DeleteMapping("/deleteChucDanh/{id}")
	public ResponseEntity<?> deleteChucDanh(@PathVariable Integer id) {
	    return ResponseEntity.ok(sChucDanh.deleteChucDanh(id));
	}
}
