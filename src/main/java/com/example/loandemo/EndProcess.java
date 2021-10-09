package com.example.loandemo;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;


@Slf4j
public class EndProcess implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        log.info("Loan Proposal Process - End");

        log.info(String.format("Process Data: %s %s %s",
                delegateExecution.getVariable("approveLoan"),
                delegateExecution.getVariable("lastApprove"),
                delegateExecution.getVariable("amount"),
                delegateExecution.getVariable("age")));

    }
}
