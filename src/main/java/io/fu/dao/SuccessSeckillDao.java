package io.fu.dao;

import org.apache.ibatis.annotations.Param;

import io.fu.entity.SuccessSeckill;

public interface SuccessSeckillDao {

	/**
	 * 插入成功秒杀记录
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	//当用代理实现接口时候参数默认为arg0 arg1....故用@Param("ParameterName")标注
	int insertSuccessSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	
	/**
	 * 根据id查询SuccessSeckill并携带秒杀对象
	 * @param seckillId
	 * @return
	 */
	SuccessSeckill queryByWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
