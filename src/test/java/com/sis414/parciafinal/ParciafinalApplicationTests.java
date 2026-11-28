package com.sis414.parciafinal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ParciafinalApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listaMeses() throws Exception {
        mockMvc.perform(get("/api/meses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(12));
    }

    @Test
    void cargaCatalogosDelDbf() throws Exception {
        mockMvc.perform(get("/api/cuentas-contables"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(39));
        mockMvc.perform(get("/api/entidades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(642));
    }

    @Test
    void usuariosNoExponenContrasena() throws Exception {
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString("psw"))))
                .andExpect(content().string(not(containsString("password"))));
    }

    @Test
    void recursoInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/api/meses/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void validaCamposObligatorios() throws Exception {
        // 'nomOfic' es obligatorio: debe responder 400 con el detalle de los errores
        mockMvc.perform(post("/api/oficinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"entidad\":\"143\",\"unidad\":\"SIS\",\"codOfic\":1}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errores.nomOfic").exists());
    }

    @Test
    void responsableMuestraElCiEnmascarado() throws Exception {
        mockMvc.perform(post("/api/unidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"entidad\":\"143\",\"unidad\":\"SIS\",\"descrip\":\"CARRERA DE INGENIERIA DE SISTEMAS\",\"ciudad\":\"Potosi\",\"estadoUni\":1}"))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/api/oficinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"entidad\":\"143\",\"unidad\":\"SIS\",\"codOfic\":1,\"nomOfic\":\"DIRECCION\"}"))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/api/responsables")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"entidad\":\"143\",\"unidad\":\"SIS\",\"codOfic\":1,\"codResp\":1,\"nomResp\":\"JUAN PEREZ\",\"cargo\":\"DIRECTOR\",\"ci\":\"12345678\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ciEnmascarado").value("*****678"))
                .andExpect(content().string(not(containsString("12345678"))));
    }
}
