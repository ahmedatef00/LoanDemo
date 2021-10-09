package com.example.loandemo;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import static java.time.LocalTime.now;


@Slf4j
public class RejectedProcess implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        log.info("Loan Approval Process - Rejected ");

        log.info(String.format("Process Data: %s %s %s %s",
                now(),
                delegateExecution.getVariable("approveLoan"),
                delegateExecution.getVariable("amount"),
                delegateExecution.getVariable("age")));
    }
}
