package com.awbdfirstproject.railwaystationapp.controller;

import com.awbdfirstproject.railwaystationapp.domain.FuelType;
import com.awbdfirstproject.railwaystationapp.domain.Train;
import com.awbdfirstproject.railwaystationapp.domain.TrainType;
import com.awbdfirstproject.railwaystationapp.services.TrainServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.Valid;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
class TrainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrainServiceInterface trainServiceInterface;

    @Test
    @WithMockUser(username = "admin", password = "C0mput3r2o1o26@", roles = "ADMIN")
    void company() throws Exception {
        mockMvc.perform(get("/admin/train"))
                .andExpect(view().name("train"));
    }

    @Test
    @WithMockUser(username = "admin", password = "C0mput3r2o1o26@", roles = "ADMIN")
    void trainFormAdmin() throws Exception {
        mockMvc.perform(get("/admin/train/train-form"))
                .andExpect(view().name("train-form"));

    }

    @Test
    @WithMockUser(username = "user", password = "C0mput3r2o1o26@", roles = "USER")
    void trainFormUser() throws Exception {
        mockMvc.perform(get("/admin/train/train-form"))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = "admin", password = "C0mput3r2o1o26@", roles = "ADMIN")
    void register() throws Exception {
        Train train = new Train();
        train.setId(100l);
        train.setTrainType(TrainType.REGIO);
        train.setFuelType(FuelType.DIESEL);
        train.setCode("A123A");
        mockMvc.perform(post("/admin/train/train-form",train))
                .andExpect(status().isOk());

    }

}