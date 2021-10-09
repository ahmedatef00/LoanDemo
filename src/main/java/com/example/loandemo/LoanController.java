package com.example.loandemo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;


@RestController
public class LoanController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RuntimeService runtimeService;

    /**
     * http://localhost:10101/camunda/api/engine/engine/default/process-definition/loan-process-v1:1:c23d33e1-293c-11ec-a01b-0242e5063be4/startForm
     * loan-process-v1:1:c23d33e1-293c-11ec-a01b-0242e5063be4/
     * @param processInstanceId
     * @return
     */


    /**
     * Start process
     * http://localhost:10101/camunda/api/engine/engine/default/process-definition/loan-process-v1:1:c23d33e1-293c-11ec-a01b-0242e5063be4/submit-form
     *
     * @param processInstanceId
     * @return
     */
    @PostMapping(value = "/loan/ready/{process-instance-id}")
    public ResponseEntity<String> loan(
            @PathVariable(name = "process-instance-id") String processInstanceId
    ) {
        WorkflowLogger.info(logger, "loan", "loan done for process instance id: " + processInstanceId);
        try {
            if (StringUtils.isEmpty(processInstanceId)) {
                WorkflowLogger.error(logger, "loan", "Process Instance Id cannot be null or empty");
                return ResponseEntity.badRequest().body("Process Instance Id cannot be null or empty");
            }
            Map<String, Object> variables = Collections.singletonMap("date", LocalDateTime.now());
            String result = runtimeService.startProcessInstanceByKey(processInstanceId, variables).getProcessInstanceId();
            return ResponseEntity.ok().body(result + " is ready to recieved.");

        } catch (Exception e) {
            WorkflowLogger.error(logger, "loan", "Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown Exception. Message: " + e.getMessage());
        }
    }

//    private String getCamundaTaskFormVariables(String taskId) throws JSONException {
//        String uri = "http://camunda:8080/engine-rest/engine/default/task/" + taskId + "/form-variables";
//        List<Object> providers = new ArrayList<Object>();
//        providers.add( new JacksonJsonProvider() );
//        WebClient client = WebClient.create(uri, providers).accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);
//        String json = client.get().readEntity(String.class);
//        return json;
//    }


}
/**
 * Start process instance
 * http://localhost:10101/engine-rest/engine/default/process-definition/loan-process-v1:1:c23d33e1-293c-11ec-a01b-0242e5063be4/start
 */