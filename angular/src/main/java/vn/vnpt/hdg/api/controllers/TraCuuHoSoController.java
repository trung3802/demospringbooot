package vn.vnpt.hdg.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.HoSoRequest;
import vn.vnpt.hdg.api.services.TraCuuHoSoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/traCuuHoSo")
public class TraCuuHoSoController {
	
	@Autowired
	TraCuuHoSoService sTraCuuHoSo;
	
	@PostMapping("/getDanhSachHoSos")
	public ResponseEntity<?> getDanhSachHoSos(@Valid @RequestBody HoSoRequest hs){
		return ResponseEntity.ok(new AppResponse(sTraCuuHoSo.getDanhSachHoSos(hs)));
	}
}