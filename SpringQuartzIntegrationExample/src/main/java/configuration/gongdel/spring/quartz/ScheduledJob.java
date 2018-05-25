package configuration.gongdel.spring.quartz;

import com.gongdel.spring.scheduling.AnotherBean;
import configuration.gongdel.spring.scheduling.AnotherBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ScheduledJob extends QuartzJobBean {

    private AnotherBean annotherBean;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        annotherBean.printAnotherMessage();
    }

    public void setAnnotherBean(AnotherBean annotherBean) {
        this.annotherBean = annotherBean;
    }
}
