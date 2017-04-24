package com.ctsi.quartzspringjndi.quartz;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.StatefulJob;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 
 * @author lb
 * @version 1.0.1
 * @Description
 * @date 2017年4月17日
 *
 */
public class DetailQuartzJobBean extends QuartzJobBean implements StatefulJob {
	
	static final Logger logger = Logger.getLogger(DetailQuartzJobBean.class.getSimpleName());

	private String targetObject;
	private String targetMethod;
	
	private ApplicationContext ctx;

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		try {
			ctx = this.getApplicationContext(context);
//			logger.info("############# execute [ " + ctx + "-" + targetObject + "-" + targetMethod + " ] at once>>>>>>");
			
			if (null != ctx) {
				Object obj = ctx.getBean(targetObject);
				
				if (null != obj) {
					Method m = null;
					try {
						m = obj.getClass().getMethod(targetMethod, new Class[] {});
						m.invoke(obj, new Object[] {});
					} 
					catch (SecurityException e) {
						logger.error(e);
						e.printStackTrace();
					} 
					catch (NoSuchMethodException e) {
						logger.error(e);
						e.printStackTrace();
					}
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new JobExecutionException(e);
		}
	}
	
	static final String APPLICATION_CONTEXT_KEY = "applicationContext";  
	
    private ApplicationContext getApplicationContext(JobExecutionContext context) {  
            ApplicationContext appCtx = null;  
            try {
				appCtx = (ApplicationContext) context.getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);
			} 
            catch (SchedulerException e) {
				e.printStackTrace();
			} 
            
            return appCtx;
    }  

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

}
