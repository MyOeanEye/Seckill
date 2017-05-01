package io.fu.dao;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.fu.entity.Seckill;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Spring/Spring-dao.xml"})
public class SeckillDaoTest {

	@Resource
	private SeckillDao seckillDao;
	@Test
	public void test()
	{
		long id = 1000;
		Seckill seck = seckillDao.queryById(id);
		System.out.println(seck);
	}
	@Test
	public void test1()
	{
		List<Seckill> secks = seckillDao.queryAll(0, 1000);
		for(Seckill s:secks)
		{
			System.out.println(s);
		}
	}
	@Test
	public void test2()
	{
		Date date = new Date();
		int count = seckillDao.reduceNumber(1000L, date);
		System.out.println(count);
	}
}
