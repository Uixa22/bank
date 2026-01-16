package koz.dev;

import koz.dev.operation.OperationsConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("koz.dev");
    var start=context.getBean(OperationsConsoleListener.class);
    start.operationsConsoleListener();

    }
}