package com.gongdel.spring.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ScheduledJob extends QuartzJobBean {

    private AnnotherBean annotherBean;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        annotherBean.printAnotherMessage();
    }

    public void setAnnotherBean(AnnotherBean annotherBean) {
        this.annotherBean = annotherBean;
    }
}
