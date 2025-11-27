package com.cinegraudos.controller;

import com.cinegraudos.domain.dto.ReservaRequestDTO;
import com.cinegraudos.domain.dto.ReservaResponseDTO;
import com.cinegraudos.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservar")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> reservar(@RequestBody ReservaRequestDTO dto) {
        ReservaResponseDTO response = reservaService.reservar(dto);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/confirmar/{idReserva}")
    public ResponseEntity<String> confirmar(@PathVariable Integer idReserva) {
        reservaService.confirmar(idReserva);
        return ResponseEntity.ok("Reservation confirmed successfully");
    }

    @PutMapping("/cancelar/{idReserva}")
    public ResponseEntity<String> cancelar(@PathVariable Integer idReserva) {
        reservaService.cancelar(idReserva);
        return ResponseEntity.ok("Reservation cancelled successfully");
    }
}