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

import vn.vnpt.hdg.api.models.ThamSo;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.ThamSoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/thamSo")
public class ThamSoController {
	
	@Autowired
	ThamSoService sThamSo;
	
	@GetMapping("/getThamSos")
	public ResponseEntity<?> getThamSos(){
		return ResponseEntity.ok(new AppResponse(sThamSo.getThamSos()));
	}
	
	@PostMapping("/addThamSo")
	public ResponseEntity<?> addThamSo(@Valid @RequestBody ThamSo ts) {
		return ResponseEntity.ok(sThamSo.addThamSo(ts));
	}
	
	@PostMapping("/editThamSo")
	public ResponseEntity<?> editThamSo(@Valid @RequestBody ThamSo ts){
	    return ResponseEntity.ok(sThamSo.editThamSo(ts));
	}
	
	@DeleteMapping("/deleteThamSo/{id}")
	public ResponseEntity<?> deleteThamSo(@PathVariable Integer id) {
	    return ResponseEntity.ok(sThamSo.deleteThamSo(id));
	}
}
