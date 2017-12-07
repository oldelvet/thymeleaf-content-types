package uk.org.oldelvet.examples.thymeleaf1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import uk.org.oldelvet.examples.thymeleaf1.BaseController;
import uk.org.oldelvet.examples.thymeleaf1.ErrorController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= { BaseController.class, ErrorController.class, ThymeConfig.class })
public class BaseControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup()
            throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void TestSimple() throws Exception {
        // Found ok with html output
        this.mockMvc.perform(get("/simple"))
            .andExpect(view().name("simple"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void TestSimple2() throws Exception {
        // Found OK with html output
        this.mockMvc.perform(get("/simple2"))
            .andExpect(view().name("simple"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void TestSimple3() throws Exception {
        // Found ok with json output
        this.mockMvc.perform(get("/simple3.json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void TestSimple4() throws Exception {
        // Not found should generate a html error page
        this.mockMvc.perform(get("/simple4"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void TestSimple5() throws Exception {
        // Not found should generate a html error page
        this.mockMvc.perform(get("/simple5.html"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void TestSimple6Ok() throws Exception {
        // Found OK giving JSON response
        this.mockMvc.perform(get("/ok/simple6.json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void TestSimple6404Html() throws Exception {
        // Not found giving HTML error page
        this.mockMvc.perform(get("/404html/simple6.json"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void TestSimple6404HtmlCs() throws Exception {
        // Not found giving HTML error page with charset override attempted in HttpServletResponse
        // Not sure if it is desirable that this should work - just done to test what happens
        this.mockMvc.perform(get("/404htmlcs/simple6.json"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("text/html;charset=ISO-8859-1"));
    }

    @Test
    public void TestSimple6404Json() throws Exception {
        // Not found giving JSON error response
        this.mockMvc.perform(get("/404json/simple6.json"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void TestSimple6404JsonCs() throws Exception {
        // Not found giving JSON template error data with charset override attempted in HttpServletResponse
        // Not sure if it is desirable that this should work - just done to test what happens
        this.mockMvc.perform(get("/404jsoncs/simple6.json"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("application/json;charset=ISO-8859-1"));
    }

    @Test
    public void TestSimple7() throws Exception {
        // Testing if the JSON template normally gives sensible results
        this.mockMvc.perform(get("/simple7.json"))
        .andExpect(view().name("errorjson.json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void TestSimple8Ok() throws Exception {
        // Found OK giving JSON response
        this.mockMvc.perform(get("/ok/simple8"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void TestSimple8404Html() throws Exception {
        // Not found giving HTML error page
        this.mockMvc.perform(get("/404html/simple8"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void TestSimple8404HtmlCs() throws Exception {
        // Not found giving HTML error page with charset override attempted in HttpServletResponse
        // Not sure if it is desirable that this should work - just done to test what happens
        this.mockMvc.perform(get("/404htmlcs/simple8"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("text/html;charset=ISO-8859-1"));
    }

    @Test
    public void TestSimple8404Json() throws Exception {
        // Not found giving JSON error response
        this.mockMvc.perform(get("/404json/simple8"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void TestSimple8404JsonCs() throws Exception {
        // Not found giving JSON template error data with charset override attempted in HttpServletResponse
        // Not sure if it is desirable that this should work - just done to test what happens
        this.mockMvc.perform(get("/404jsoncs/simple8"))
            .andExpect(view().name("errorpage"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("application/json;charset=ISO-8859-1"));
    }
}
