package com.woowacourse.pelotonbackend.report.presentation;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowacourse.pelotonbackend.report.application.ReportService;
import com.woowacourse.pelotonbackend.report.domain.ReportFixture;
import com.woowacourse.pelotonbackend.support.BearerAuthInterceptor;

@SpringBootTest
class ReportControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @MockBean
    private BearerAuthInterceptor authInterceptor;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .alwaysDo(print())
            .build();
    }

    @Test
    void createReport() throws Exception {
        final Long createdReportId = 10L;
        given(authInterceptor.preHandle(any(), any(), any())).willReturn(true);
        when(reportService.createReport(any(ReportCreateContent.class))).thenReturn(createdReportId);

        MvcResult mvcResult = mockMvc.perform(
            post("/api/reports")
                .content(objectMapper.writeValueAsBytes(ReportFixture.createRequestContent()))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();

        assertThat(mvcResult.getResponse().getHeader("Location"))
            .isEqualTo(String.format("/api/reports/%d", createdReportId));
    }
}
