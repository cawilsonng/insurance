package com.wilson.insurance.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilson.insurance.constant.ControllerConstants;
import com.wilson.insurance.models.quotation.carInsurance.RequestDto;
import com.wilson.insurance.models.quotation.carInsurance.ResponseDto;
import com.wilson.insurance.services.QuotationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;

class QuotationControllerTest {
    @Mock
    private QuotationService quotationService;
    @InjectMocks
    private QuotationController quotationController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(quotationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCarInsuranceQuote() throws Exception {
        RequestDto requestDto = new RequestDto();
        ResponseDto responseDto = ResponseDto.builder().success(true).build();
        given(quotationService.getCarInsuranceQuotation(requestDto)).willReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post(ControllerConstants.Quotation.BASE + "/" + ControllerConstants.Quotation.CAR_QUOTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(responseDto.isSuccess()));
    }
}