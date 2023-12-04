package vn.vnpt.hdg.api.controllers;

import java.util.List;

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

import vn.vnpt.hdg.api.models.DonVi;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.TreeItem;
import vn.vnpt.hdg.api.services.DonViService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/donVi")
public class DonViController {
	
	@Autowired
	DonViService sDonVi;
	
	@GetMapping("/getDonVis")
	public ResponseEntity<?> getDonVis(){
		return ResponseEntity.ok(new AppResponse(sDonVi.getDonVis()));
	}
	
	@GetMapping("/getTreeDonVi")
    public ResponseEntity<?> getTreeDonVis() {
        List<TreeItem> tree = sDonVi.getTreeDonVi();
        return ResponseEntity.ok(tree);
    }
	
	@GetMapping("/getDonViTheoDois")
	public ResponseEntity<?> getDonViTheoDois(){
		return ResponseEntity.ok(new AppResponse(sDonVi.getDonViTheoDois()));
	}
	
	@PostMapping("/getDonViCons/{id}")
	public ResponseEntity<?> getDonViCons(@PathVariable Integer id){
		return ResponseEntity.ok(sDonVi.getDonViCons(id));
	}
	
	@PostMapping("/addDonVi")
	public ResponseEntity<?> addDonVi(@Valid @RequestBody DonVi dv) {
		return ResponseEntity.ok(sDonVi.addDonVi(dv));
	}
	
	@PostMapping("/editDonVi")
	public ResponseEntity<?> editDonVi(@Valid @RequestBody DonVi dv){
	    return ResponseEntity.ok(sDonVi.editDonVi(dv));
	}
	
	@DeleteMapping("/deleteDonVi/{id}")
	public ResponseEntity<?> deleteDonVi(@PathVariable Integer id) {
	    return ResponseEntity.ok(sDonVi.deleteDonVi(id));
	}
}
