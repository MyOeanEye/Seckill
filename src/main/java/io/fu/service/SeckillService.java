package io.fu.service;

import java.util.List;

import io.fu.dto.Exposer;
import io.fu.dto.SeckillExecution;
import io.fu.entity.Seckill;
import io.fu.exception.RepeatKillException;
import io.fu.exception.SeckillCloseException;
import io.fu.exception.SeckillException;

/**
 * 站在使用者的角度定义接口
 * @author Mr F
 *
 */
public interface SeckillService {

	/**
	 * 返回所有秒杀商品
	 * @return
	 */
	List<Seckill> getAllSeckill();
	/**
	 * 根据id返回一个商品
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	/**
	 * 秒杀开启时时暴露出秒杀地址
	 * 否则输出系统时间和秒杀时间
	 * 
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
