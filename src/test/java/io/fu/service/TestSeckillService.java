package io.fu.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.fu.dto.Exposer;
import io.fu.dto.SeckillExecution;
import io.fu.entity.Seckill;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Spring/Spring-dao.xml",
		"classpath:Spring/Spring-service.xml"})
public class TestSeckillService {

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Resource
	private SeckillService seckillService;
	@Test
	public void test() {
	/*	List<Seckill> list  = seckillService.getAllSeckill();
		logger.info("list={}", list);
		
		long id = 1000L;
		Seckill s = seckillService.getById(id);
		logger.info("seckill={}", s);*/
		/*Exposer exposer = seckillService.exportSeckillUrl(1000L);
		logger.info("exposer={}",exposer);*/
		
		Exposer exposer = seckillService.exportSeckillUrl(1001L);
		if(exposer.isExposed())
		{
			long userPhone = 999999L;
			String md5 = exposer.getMd5();
			
			SeckillExecution seckillExecution = seckillService.executeSeckill(1001L, userPhone, md5);
			
			logger.info("result={}", seckillExecution);
		}
		else
		{
			logger.warn("result={}",exposer);
		}
		
	}

}
