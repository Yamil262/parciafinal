package com.sis414.parciafinal.dto;

/** Nueva oficina y responsable (CODOFIC, CODRESP) del activo transferido. */
public record TransferirRequest(Integer codOfic, Integer codResp, String usuar) {}
