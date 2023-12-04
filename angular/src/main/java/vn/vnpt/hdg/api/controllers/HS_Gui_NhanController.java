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

import vn.vnpt.hdg.api.models.HS_Gui_Nhan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.HS_Gui_NhanService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/hS_Gui_Nhan")
public class HS_Gui_NhanController {
	
	@Autowired
	HS_Gui_NhanService sHS_Gui_Nhan;
	
	@GetMapping("/getHS_Gui_Nhans")
	public ResponseEntity<?> getHS_Gui_Nhans(){
		return ResponseEntity.ok(new AppResponse(sHS_Gui_Nhan.getHS_Gui_Nhans()));
	}
	
	@PostMapping("/addHS_Gui_Nhan")
	public ResponseEntity<?> addHS_Gui_Nhan(@Valid @RequestBody HS_Gui_Nhan cd) {
		return ResponseEntity.ok(sHS_Gui_Nhan.addHS_Gui_Nhan(cd));
	}
	
	@PostMapping("/editHS_Gui_Nhan")
	public ResponseEntity<?> editHS_Gui_Nhan(@Valid @RequestBody HS_Gui_Nhan cd){
	    return ResponseEntity.ok(sHS_Gui_Nhan.editHS_Gui_Nhan(cd));
	}
	
	@DeleteMapping("/deleteHS_Gui_Nhan/{id}")
	public ResponseEntity<?> deleteHS_Gui_Nhan(@PathVariable Integer id) {
	    return ResponseEntity.ok(sHS_Gui_Nhan.deleteHS_Gui_Nhan(id));
	}
}
