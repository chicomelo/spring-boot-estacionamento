package br.com.estacionamento.Estacionamento.controller;

import br.com.estacionamento.Estacionamento.model.vo.CarroVO;
import br.com.estacionamento.Estacionamento.model.vo.MarcaVO;
import br.com.estacionamento.Estacionamento.model.vo.ModeloVO;
import br.com.estacionamento.Estacionamento.security.JwtAuthFilter;
import br.com.estacionamento.Estacionamento.security.JwtService;
import br.com.estacionamento.Estacionamento.service.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarroController.class)
@AutoConfigureMockMvc(addFilters = false)
class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarroService carroService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthFilter jwtAuthFilter;

    @Test
    void buscarCarroPorId() throws Exception {
        // Arrange
        MarcaVO marcaVO = new MarcaVO(1L, "Fiat");
        ModeloVO modeloVO = new ModeloVO(1L, "Uno", marcaVO);
        CarroVO carroVO = new CarroVO(10L, "ABC-1234", modeloVO, "Preto");

        when(carroService.buscarCarroPorId(10L)).thenReturn(carroVO);

        // Act & Assert
        mockMvc.perform(get("/api/carros/10").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.placa").value("ABC-1234"))
                .andExpect(jsonPath("$.modelo.nome").value("Uno"))
                .andExpect(jsonPath("$.modelo.marca.nome").value("Fiat"))
                .andExpect(jsonPath("$.cor").value("Preto"));
    }
}
