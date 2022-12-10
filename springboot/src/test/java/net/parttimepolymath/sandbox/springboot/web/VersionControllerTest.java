package net.parttimepolymath.sandbox.springboot.web;

import net.parttimepolymath.sandbox.springboot.configuration.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VersionController.class)
class VersionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Version version;

    @BeforeEach
    void setUp() {
        Mockito.when(version.getName()).thenReturn("test");
        Mockito.when(version.getBuild()).thenReturn("2020-07-05 11:54:32");
        Mockito.when(version.getProfile()).thenReturn("release");
        Mockito.when(version.getVersion()).thenReturn("1.0");
    }

    @Test
    void testVersion() throws Exception {
        for (String path : Arrays.asList("/api", "/api/version")) {
            mockMvc.perform(get(path))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.version", is("1.0")))
                    .andExpect(jsonPath("$.name", is("test")))
                    .andExpect(jsonPath("$.build", is("2020-07-05 11:54:32")))
                    .andExpect(jsonPath("$.profile", is("release")))
            ;
        }
    }

    @Test
    void testBad() throws Exception {
        mockMvc.perform(get("/api/v")).andExpect(status().is4xxClientError());

    }
}