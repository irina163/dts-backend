package uk.gov.hmcts.reform.dev.openapi;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OpenAPIPublisherTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void OpenApiTest() throws Exception {
        mockMvc.perform(get("/v3/api-docs"))
               .andExpect(status().isOk());
        
    }
}