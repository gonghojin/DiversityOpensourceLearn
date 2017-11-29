package com.websystique.springbatch;

import org.springframework.batch.item.ItemProcessor;

import com.websystique.springbatch.model.ExamResult;

public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult> {

	public ExamResult process(ExamResult result) throws Exception {
		System.out.println("Processing result :"+result);
		
		/*
		 * 
		 * 75 이상 값만 return
		 * 
		 */
		if(result.getPercentage() < 75){
			return null;
		}	
		
		return result;
	}
	
}
