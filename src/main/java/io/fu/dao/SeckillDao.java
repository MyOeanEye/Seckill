package io.fu.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.fu.entity.Seckill;

public interface SeckillDao {

	/**
	 * �����
	 * @param seckillId
	 * @param killtime
	 * @return
	 */
	 int reduceNumber(@Param("seckillId")long seckillId,@Param("killtime")Date killtime);
	 
	 /**
	  * ����id��ѯ��ɱ����
	  * @param seckillId
	  * @return
	  */
	 Seckill queryById(long seckillId);
	 
	 /**
	  * ����ƫ������ѯ��ɱ��Ʒ�б�
	  * @param offset
	  * @param limit
	  * @return
	  */
	 List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
	 
}
