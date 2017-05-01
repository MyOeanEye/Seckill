package io.fu.service;

import java.util.List;

import io.fu.dto.Exposer;
import io.fu.dto.SeckillExecution;
import io.fu.entity.Seckill;
import io.fu.exception.RepeatKillException;
import io.fu.exception.SeckillCloseException;
import io.fu.exception.SeckillException;

/**
 * վ��ʹ���ߵĽǶȶ���ӿ�
 * @author Mr F
 *
 */
public interface SeckillService {

	/**
	 * ����������ɱ��Ʒ
	 * @return
	 */
	List<Seckill> getAllSeckill();
	/**
	 * ����id����һ����Ʒ
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	/**
	 * ��ɱ����ʱʱ��¶����ɱ��ַ
	 * �������ϵͳʱ�����ɱʱ��
	 * 
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * ִ����ɱ����
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
