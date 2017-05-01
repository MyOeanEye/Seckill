package io.fu.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import io.fu.dao.SeckillDao;
import io.fu.dao.SuccessSeckillDao;
import io.fu.dto.Exposer;
import io.fu.dto.SeckillExecution;
import io.fu.entity.Seckill;
import io.fu.entity.SuccessSeckill;
import io.fu.enums.SeckillState;
import io.fu.exception.RepeatKillException;
import io.fu.exception.SeckillCloseException;
import io.fu.exception.SeckillException;
import io.fu.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {
	
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Resource
	private SeckillDao seckillDao;

	@Resource
	private SuccessSeckillDao successSeckillDao;
	
	
	//混淆字符串 md5
	private final String slat = "HJEIOWQU*(#U@!(*#($U!Q(u40）*#！@#）（*￥#";
	public List<Seckill> getAllSeckill() {
		// TODO Auto-generated method stub
		return seckillDao.queryAll(0, 10);
	}

	public Seckill getById(long seckillId) {
		// TODO Auto-generated method stub
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);
		
		if(seckill==null) return new Exposer(false, seckillId);
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if(nowTime.getTime()<startTime.getTime()
				|| endTime.getTime()<nowTime.getTime())
		{
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		String md5 = getMd5(seckillId);//TODO
		return new Exposer(true, md5, seckillId);
	}

	/**
	 * 使用注解控制方法的优点
	 * 一、可以明确那个方法有事物
	 * 保证 没有事物的方法执行效率高
	 */
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		try {
			//重复提交验证
			if(md5==null || !md5.equals(getMd5(seckillId)))
				throw new RepeatKillException("repeat submit");
			//执行秒杀逻辑
			Date nowTime = new Date();
			//减库存
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			//没有减则出现秒杀结束或者时间不在秒杀范围内
			if(updateCount<=0)
			{
				throw new SeckillCloseException("sekcill has closed");
			}
			else
			{
				int insertCount = successSeckillDao.insertSuccessSeckill(seckillId, userPhone);
				if(insertCount<=0){
					//记录重复
					throw new RepeatKillException("重复提交");
				}else {
					//秒杀成功
					SuccessSeckill successSeckill = successSeckillDao.queryByWithSeckill(seckillId, userPhone);
					return  new SeckillExecution(seckillId,SeckillState.SUCCESS,successSeckill);
				}
				
			}
		}catch (RepeatKillException e1) {
			throw e1;
		}catch (SeckillCloseException e2) {
			throw e2;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SeckillException("seckill inner error"+e.getMessage());
		}
	}
	
	//获取md5  用slat做混淆 防止被推算出来
	private String getMd5(long seckillId)
	{
		String base = seckillId +"/" +slat;
		
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
