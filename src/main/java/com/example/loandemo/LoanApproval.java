package com.example.loandemo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
//Test The manual Approve
public class LoanApproval implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


        boolean approve = (boolean) delegateExecution.getVariable("approveLoan");
//        long amount = (long) delegateExecution.getVariable("amount");
//        System.out.println("amount is " + ">>> " + amount + "\n");
//
//        long age = (long) delegateExecution.getVariable("age");
//        System.out.println("age is " + ">>> " + age + "\n");
//
//        if (amount <= 5000 && age <= 60) {
//            approve = true;
//        } else if (age <= 60 && amount > 5000) {
//            approve = true;
//
//        }
//
        delegateExecution.setVariable("lastApprove", approve);
        System.out.println("Last approve :: " + delegateExecution.getVariable("lastApprove"));

    }
}
