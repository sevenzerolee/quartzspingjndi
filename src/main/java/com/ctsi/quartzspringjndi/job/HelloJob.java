package com.ctsi.quartzspringjndi.job;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author lb
 * @version 1.0.1
 * @Description
 * @date 2017年3月30日
 * 
 */
public class HelloJob implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4558319824928134118L;

	/**
	 * 执行10秒
	 */
	public void doStudy() {
		System.out.println("############## Start It is hello " + new Date());
		
		try {
			Thread.sleep(60 * 1000L);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("############## End   It is hello " + new Date());
	}

}
