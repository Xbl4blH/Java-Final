package integration;


import com.example.finaljava.FinalJavaApplication;
import com.example.finaljava.models.Firm;
import com.example.finaljava.services.FirmService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@EnableJms
@SpringBootTest(classes = FinalJavaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FirmControllerIntegrationTest {
    @LocalServerPort
    private int port;
    private String urlTemplate = "http://localhost:" + port + "/firm/";
    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @MockBean
    private FirmService firmService;

    @Autowired
    public FirmControllerIntegrationTest(MockMvc mvc, ObjectMapper objectMapper) {
        this.mvc = mvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void testGetAllFirmm() throws Exception {
        List<Firm> firms = Arrays.asList(new Firm());
        when(firmService.getAll()).thenReturn(firms);

        String jsonResponse = objectMapper.writeValueAsString(firms);
        mvc.perform(get(urlTemplate+"all"))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonResponse));

        verify(firmService).getAll();
    }

    @Test
    void testGetAllFirm() throws Exception {
        Firm firm = new Firm();
        Integer id = 1;
        when(firmService.findById(id)).thenReturn(firm);
        String jsonResponse = objectMapper.writeValueAsString(firm);

        mvc.perform(get(urlTemplate+id))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonResponse));

        verify(firmService).getAll();
    }
}
