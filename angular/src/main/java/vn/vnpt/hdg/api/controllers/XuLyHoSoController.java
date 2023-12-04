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

import vn.vnpt.hdg.api.models.TT_Ho_So;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.services.XuLyHoSoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/xuLyHoSo")
public class XuLyHoSoController {
	
	@Autowired
	XuLyHoSoService sXuLyHoSo;
	
	@GetMapping("/getHoSoXuLys")
	public ResponseEntity<?> getHoSoXuLys(){
		return ResponseEntity.ok(new AppResponse(sXuLyHoSo.getHoSoXuLys()));
	}
	
	@PostMapping("/editHoSoXuLy")
	public ResponseEntity<?> editHoSoXuLy(@Valid @RequestBody TT_Ho_So hs){
	    return ResponseEntity.ok(sXuLyHoSo.editHoSoXuLy(hs));
	}
	
	@DeleteMapping("/deleteHoSoXuLy/{id}")
	public ResponseEntity<?> deleteChucDanh(@PathVariable Integer id) {
	    return ResponseEntity.ok(sXuLyHoSo.deleteHoSoXuLy(id));
	}
	
	@PostMapping("/moHoSoXuLy/{id}")
	public ResponseEntity<?> moChucDanh(@PathVariable Integer id) {
	    return ResponseEntity.ok(sXuLyHoSo.moHoSoXuLy(id));
	}
	
	@PostMapping("/dongHoSoXuLy/{id}")
	public ResponseEntity<?> dongHoSoXuLy(@PathVariable Integer id) {
	    return ResponseEntity.ok(sXuLyHoSo.dongHoSoXuLy(id));
	}
	
	@PostMapping("/luuTruHoSoXuLy/{id}")
	public ResponseEntity<?> luuTruHoSoXuLy(@PathVariable Integer id) {
	    return ResponseEntity.ok(sXuLyHoSo.luuTruHoSoXuLy(id));
	}
}
