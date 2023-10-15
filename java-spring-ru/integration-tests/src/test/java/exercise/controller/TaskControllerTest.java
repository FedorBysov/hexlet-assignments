package exercise.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.transaction.annotation.Transactional;

// END
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
// BEGIN
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvcTest;

    @Autowired
    ObjectMapper om;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Faker faker;

    @Autowired
    private TaskRepository taskRepositoryTest;


    private Task taskTest;

    @BeforeEach
    public void setUp() {
        taskTest = new Task();
        taskTest.setDescription("task1 description");
        taskTest.setTitle("task1 title");
        taskRepositoryTest.save(taskTest);
    }

    @Test
    public void showTest() throws Exception {
        mockMvcTest.perform(get("/tasks/" + taskTest.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(taskTest)));
    }


    @Test
    public void createTest() throws Exception {
        var task = new Task();
        task.setDescription("task description");
        task.setTitle("task title");

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        mockMvcTest.perform(request)
                .andExpect(status().isCreated());
    }


    @Test
    public void updateTest() throws Exception {
        var task = new Task();
        task.setDescription("new task description");
        task.setTitle("new task title");

        var request = put("/tasks/{id}", taskTest.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        mockMvcTest.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvcTest.perform(delete("/tasks/" + taskTest.getId()))
                .andExpect(status().isOk());
        assertThat(taskRepositoryTest.findAll().isEmpty());
    }

}
// END
