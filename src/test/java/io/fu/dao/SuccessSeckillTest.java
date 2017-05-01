package io.fu.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.fu.entity.Seckill;
import io.fu.entity.SuccessSeckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Spring/Spring-dao.xml"})
public class SuccessSeckillTest {
	@Resource
	private SuccessSeckillDao successSeckillDao;
	
	@Test
	public void test()
	{
		SuccessSeckill se = successSeckillDao.queryByWithSeckill(1000L, 87167258L);
		System.out.println(se);
		System.out.println(se.getSeckill());
	}
}
