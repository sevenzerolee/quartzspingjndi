package com.ctsi.quartzspringjndi.job;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author lb
 * @version 1.0.1
 * @Description
 * @date 2017年4月17日
 *
 */
public class WorldJob implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4297964891946540442L;

	/**
	 * 执行8秒
	 */
	public void execute() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$ start " + new Date());
		try {
			Thread.sleep(90 * 1000L);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("$$$$$$$$$$$$$$$$$$$$ end   " + new Date());
	}

}
