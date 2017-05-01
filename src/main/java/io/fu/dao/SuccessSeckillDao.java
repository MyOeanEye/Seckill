package io.fu.dao;

import org.apache.ibatis.annotations.Param;

import io.fu.entity.SuccessSeckill;

public interface SuccessSeckillDao {

	/**
	 * ����ɹ���ɱ��¼
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	//���ô���ʵ�ֽӿ�ʱ�����Ĭ��Ϊarg0 arg1....����@Param("ParameterName")��ע
	int insertSuccessSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	
	/**
	 * ����id��ѯSuccessSeckill��Я����ɱ����
	 * @param seckillId
	 * @return
	 */
	SuccessSeckill queryByWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
