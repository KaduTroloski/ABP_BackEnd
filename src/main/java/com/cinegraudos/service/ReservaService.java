package com.cinegraudos.service;

import com.cinegraudos.domain.dto.ReservaRequestDTO;
import com.cinegraudos.domain.dto.ReservaResponseDTO;
import com.cinegraudos.domain.entity.*;
import com.cinegraudos.domain.mapper.ReservaMapper;
import com.cinegraudos.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReservaService {

    private final ReservasRepository reservasRepository;
    private final AssentoReservaRepository assentoReservaRepository;
    private final SessoesRepository sessoesRepository;
    private final UsuariosRepository usuariosRepository;
    private final AssentosRepository assentosRepository;

    public ReservaService(ReservasRepository reservasRepository,
                          AssentoReservaRepository assentoReservaRepository,
                          SessoesRepository sessoesRepository,
                          UsuariosRepository usuariosRepository,
                          AssentosRepository assentosRepository) {
        this.reservasRepository = reservasRepository;
        this.assentoReservaRepository = assentoReservaRepository;
        this.sessoesRepository = sessoesRepository;
        this.usuariosRepository = usuariosRepository;
        this.assentosRepository = assentosRepository;
    }


    public ReservaResponseDTO reservar(ReservaRequestDTO dto) {
        SessoesEntity sessao = sessoesRepository.findById(dto.idSessao())
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        UsuariosEntity usuario = usuariosRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ReservasEntity reserva = new ReservasEntity();
        reserva.setSessao(sessao);
        reserva.setUsuario(usuario);
        reserva.setStatus("pagamento_pendente");

        reservasRepository.save(reserva);

        List<Integer> assentosReservados = assentoReservaRepository
                .findAssentosReservadosPorSessao(dto.idSessao());

        List<Integer> duplicados = dto.idAssentos().stream()
                .filter(assentosReservados::contains)
                .toList();

        if (!duplicados.isEmpty()) {
            throw new IllegalStateException("Some seats are already reserved: " + duplicados);
        }

        List<AssentoReservaEntity> assentos = dto.idAssentos().stream()
                .map(idAssento -> {
                    AssentosEntity assentoEntity = assentosRepository.findById(idAssento)
                            .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

                    AssentoReservaEntity ar = new AssentoReservaEntity();
                    ar.setReserva(reserva);
                    ar.setAssento(assentoEntity);
                    float precoFinal = sessao.getPreco_base() * dto.idAssentos().size();
                    ar.setPreco_final(precoFinal);

                    return ar;
                })
                .toList();

        assentoReservaRepository.saveAll(assentos);

        return ReservaMapper.toDTO(reserva, assentos);
    }


    public void confirmar(Integer idReserva) {
        ReservasEntity reserva = reservasRepository.findById(idReserva)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reserva.setStatus("confirmado");
        reservasRepository.save(reserva);
    }


    public void cancelar(Integer idReserva) {
        ReservasEntity reserva = reservasRepository.findById(idReserva)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reserva.setStatus("cancelado");
        reservasRepository.save(reserva);
    }
}
