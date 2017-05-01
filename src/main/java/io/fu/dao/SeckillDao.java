package io.fu.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.fu.entity.Seckill;

public interface SeckillDao {

	/**
	 * 减库存
	 * @param seckillId
	 * @param killtime
	 * @return
	 */
	 int reduceNumber(@Param("seckillId")long seckillId,@Param("killtime")Date killtime);
	 
	 /**
	  * 根据id查询秒杀对象
	  * @param seckillId
	  * @return
	  */
	 Seckill queryById(long seckillId);
	 
	 /**
	  * 根据偏移量查询秒杀商品列表
	  * @param offset
	  * @param limit
	  * @return
	  */
	 List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
	 
}
