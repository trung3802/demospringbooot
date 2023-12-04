package vn.vnpt.hdg.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.HoSoRequest;
import vn.vnpt.hdg.api.services.DuyetLuuHoSoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/duyetLuuHoSo")
public class DuyetLuuHoSoController {
	
	@Autowired
	DuyetLuuHoSoService sDuyetLuuHoSo;
	
	@PostMapping("/getDanhSachHoSos")
	public ResponseEntity<?> getDanhSachHoSos(@Valid @RequestBody HoSoRequest hs){
		return ResponseEntity.ok(new AppResponse(sDuyetLuuHoSo.getDanhSachHoSos(hs)));
	}
	
	@PostMapping("/duyetLuuHoSoDuyetLuu/{id}")
	public ResponseEntity<?> duyetLuuHoSoDuyetLuu(@PathVariable Integer id) {
	    return ResponseEntity.ok(sDuyetLuuHoSo.duyetLuuHoSoDuyetLuu(id));
	}
	
	@PostMapping("/tuChoiHoSoDuyetLuu/{id}")
	public ResponseEntity<?> tuChoiHoSoDuyetLuu(@PathVariable Integer id) {
	    return ResponseEntity.ok(sDuyetLuuHoSo.tuChoiHoSoDuyetLuu(id));
	}
}