package org.starchartlabs.river.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.hypermedia.HypermediaDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.starchartlabs.river.main.webapp.app.api.IProjectAppService;
import org.starchartlabs.river.main.webapp.app.model.LinkView;
import org.starchartlabs.river.main.webapp.app.model.MetaDataView;
import org.starchartlabs.river.main.webapp.app.model.PageView;
import org.starchartlabs.river.main.webapp.app.model.ProjectView;
import org.starchartlabs.river.main.webapp.app.model.RequestPaths;
import org.starchartlabs.river.main.webapp.server.impl.ProjectRestServer;
import org.starchartlabs.river.test.util.StarchartLinkExtractor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

public class ProjectRestServerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private IProjectAppService projectAppService;
    
    private final ManualRestDocumentation restDocumentation = new ManualRestDocumentation();
    
    private MockMvc mockMvc;
    
    @BeforeTest
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void setUp(Method method) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ProjectRestServer(projectAppService))
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation)
                        .operationPreprocessors()
                        .withResponseDefaults(Preprocessors.prettyPrint()))
                .build();
        this.restDocumentation.beforeTest(getClass(), method.getName());
    }
    
    @AfterMethod
    public void tearDown() {
        this.restDocumentation.afterTest();
    }
    
    @Test
    public void projects() throws Exception {
        UUID projectId = UUID.randomUUID();
        List<LinkView> links = Lists.newArrayList(new LinkView("userflows", RequestPaths.getUserFlowListUrl(projectId)));
        ProjectView project = new ProjectView("Sample Project", new MetaDataView(RequestPaths.getProjectUrl(projectId), links));
        
        Mockito.when(projectAppService.get())
        .thenReturn(new PageView<>(Lists.newArrayList(project), new MetaDataView(RequestPaths.getProjectUrl(projectId))));
        
        this.mockMvc.perform(MockMvcRequestBuilders.get("/projects"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcRestDocumentation.document("projects"));
    }
    
    @Test
    public void singleProject() throws Exception {
        UUID projectId = UUID.randomUUID();
        List<LinkView> links = Lists.newArrayList(new LinkView("userflows", RequestPaths.getUserFlowListUrl(projectId)));
        ProjectView project = new ProjectView("Sample Project", new MetaDataView(RequestPaths.getProjectUrl(projectId), links));
        
        Mockito.when(projectAppService.get(projectId))
        .thenReturn(Optional.of(project));
        
        this.mockMvc.perform(MockMvcRequestBuilders.get("/projects/" + projectId.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcRestDocumentation.document("project",
                PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("name")
                            .description("The name of the project"),
                        PayloadDocumentation.subsectionWithPath("_meta")
                            .description("Meta data describing the response")),
               HypermediaDocumentation.links(new StarchartLinkExtractor(),
                       HypermediaDocumentation.linkWithRel("userflows").description("Link to the user-flows for this project"))));
    }
    
}
