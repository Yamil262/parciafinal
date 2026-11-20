package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.OrgUserDto;
import com.sis414.parciafinal.entity.OrgUser;
import com.sis414.parciafinal.repository.OrgUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrgUserService {

    private final OrgUserRepository repository;

    public OrgUserService(OrgUserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<OrgUserDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public OrgUserDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public OrgUserDto create(OrgUserDto dto) {
        OrgUser entity = new OrgUser();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public OrgUserDto update(Long id, OrgUserDto dto) {
        OrgUser entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public OrgUser getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrgUser no encontrado: " + id));
    }

    public OrgUserDto toDto(OrgUser e) {
        return new OrgUserDto(
                e.getId(),
                e.getType(),
                e.getIdUser(),
                e.getName(),
                e.getReadonly(),
                e.getCkval(),
                e.getData(),
                e.getUpdated()
        );
    }

    private void apply(OrgUserDto dto, OrgUser e) {
        e.setType(dto.type());
        e.setIdUser(dto.idUser());
        e.setName(dto.name());
        e.setReadonly(dto.readonly());
        e.setCkval(dto.ckval());
        e.setData(dto.data());
        e.setUpdated(dto.updated());
    }
}
