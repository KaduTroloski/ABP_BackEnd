package com.cinegraudos.service;

import com.cinegraudos.domain.dto.SessaoRequestDTO;
import com.cinegraudos.domain.dto.SessaoResponseDTO;
import com.cinegraudos.domain.dto.SessaoUpdateDTO;
import com.cinegraudos.domain.entity.CinemasEntity;
import com.cinegraudos.domain.entity.FilmesEntity;
import com.cinegraudos.domain.entity.SalasEntity;
import com.cinegraudos.domain.entity.SessoesEntity;
import com.cinegraudos.domain.mapper.SessaoMapper;
import com.cinegraudos.repository.FilmesRepository;
import com.cinegraudos.repository.SalasRepository;
import com.cinegraudos.repository.SessoesRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessaoService {

    private final SalasRepository salasRepository;
    private final FilmesRepository filmesRepository;
    private final SessoesRepository sessoesRepository;

    public SessaoService(SalasRepository salaRepository, FilmesRepository filmeRepository, SessoesRepository sessaoRepository) {
        this.salasRepository = salaRepository;
        this.filmesRepository = filmeRepository;
        this.sessoesRepository = sessaoRepository;
    }

    public List<SessaoResponseDTO> generateSessions(SessaoRequestDTO dto) {
        SalasEntity sala = salasRepository.findById(dto.idSala())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        CinemasEntity cinema = sala.getCinema();

        FilmesEntity filme = filmesRepository.findById(dto.idFilme())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        Duration duracaoFilme = Duration.ofMillis(filme.getDuracao());
        Duration interval = Duration.ofMillis(dto.intervalo());

        LocalDate hoje = LocalDate.now();
        if (dto.data().isBefore(hoje)) {
            throw new IllegalStateException("It is not possible to create for previous days.");
        }
        LocalTime horaAbertura = cinema.getHorario_abertura();
        LocalTime horaFechamento = cinema.getHorario_fechamento();

        Instant abertura = dto.data().atTime(horaAbertura)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Instant fechamento = dto.data().atTime(horaFechamento)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Instant inicioDia = dto.data().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant fimDia = dto.data().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        boolean alreadySessions = !sessoesRepository
                .findBySalaAndHorarioInicioBetween(sala, inicioDia, fimDia)
                .isEmpty();

        if (alreadySessions) {
            throw new IllegalStateException("Session already exists to this date and room");
        }

        Instant beggin = abertura;
        List<SessoesEntity> sessoes = new ArrayList<>();

        while (!beggin.plus(duracaoFilme).isAfter(fechamento)) {
            Instant fim = beggin.plus(duracaoFilme);

            SessoesEntity sessao = new SessoesEntity();
            sessao.setSala(sala);
            sessao.setFilme(filme);
            sessao.setHorarioInicio(beggin);
            sessao.setHorarioFim(fim);
            sessao.setPreco_base(dto.preco());

            sessoes.add(sessao);

            beggin = fim.plus(interval);
        }
        return sessoesRepository.saveAll(sessoes)
                .stream()
                .map(SessaoMapper::toDTO)
                .toList();
    }

    public List<SessaoResponseDTO> findAll() {
        return sessoesRepository.findAll()
                .stream()
                .map(SessaoMapper::toDTO)
                .toList();
    }

    public SessaoResponseDTO findById(Integer id) {
        SessoesEntity sessao = sessoesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        return SessaoMapper.toDTO(sessao);
    }

    public void update(Integer id, SessaoUpdateDTO dto) {
        SessoesEntity sessao = sessoesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        LocalDate dataExistente = sessao.getHorarioInicio()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate dataNova = dto.horarioInicio()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        if (!dataExistente.equals(dataNova)) {
            throw new IllegalStateException("It is not allowed to change the session date");
        }

        if (dto.hasPreco()) sessao.setPreco_base(dto.preco());
        if (dto.hasHorarioInicio()) sessao.setHorarioInicio(dto.horarioInicio());
        if (dto.hasHorarioFim()) sessao.setHorarioFim(dto.horarioFim());

        sessoesRepository.save(sessao);
    }

    public void delete(Integer id) {
        SessoesEntity sessao = sessoesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        sessoesRepository.delete(sessao);
    }
}
