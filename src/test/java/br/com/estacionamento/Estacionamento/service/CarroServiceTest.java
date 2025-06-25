package br.com.estacionamento.Estacionamento.service;

import br.com.estacionamento.Estacionamento.model.Carro;
import br.com.estacionamento.Estacionamento.model.Marca;
import br.com.estacionamento.Estacionamento.model.Modelo;
import br.com.estacionamento.Estacionamento.model.vo.CarroRequestVO;
import br.com.estacionamento.Estacionamento.model.vo.CarroVO;
import br.com.estacionamento.Estacionamento.repository.CarroRepository;
import br.com.estacionamento.Estacionamento.repository.ModeloRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarroServiceTest {

    @Autowired
    private CarroService carroService;

    @MockBean
    private CarroRepository carroRepository;

    @MockBean
    private ModeloRepository modeloRepository;

    @Test
    void criarCarroTest() {
        // Arrange
        Marca marca = new Marca();
        marca.setId(1L);
        marca.setNome("Fiat");

        Modelo modelo = new Modelo();
        modelo.setId(1L);
        modelo.setNome("Uno");;
        modelo.setMarca(marca);

        CarroRequestVO request = new CarroRequestVO();
        request.setPlaca("ABC-1234");
        request.setCor("Preto");
        request.setModeloId(1L);

        Carro carroSalvo = Carro.builder()
                .id(10L)
                .placa("ABC-1234")
                .cor("Preto")
                .modelo(modelo)
                .build();

        when(modeloRepository.findById(1L)).thenReturn(Optional.of(modelo));
        when(carroRepository.save(org.mockito.ArgumentMatchers.any(Carro.class))).thenReturn(carroSalvo);

        // Act
        CarroVO resultado = carroService.criarCarro(request);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getPlaca()).isEqualTo("ABC-1234");
        assertThat(resultado.getModelo().getNome()).isEqualTo("Uno");
        assertThat(resultado.getModelo().getMarca().getNome()).isEqualTo("Fiat");
    }
}
