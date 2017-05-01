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
	
	
	//�����ַ��� md5
	private final String slat = "HJEIOWQU*(#U@!(*#($U!Q(u40��*#��@#����*��#";
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
	 * ʹ��ע����Ʒ������ŵ�
	 * һ��������ȷ�Ǹ�����������
	 * ��֤ û������ķ���ִ��Ч�ʸ�
	 */
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		try {
			//�ظ��ύ��֤
			if(md5==null || !md5.equals(getMd5(seckillId)))
				throw new RepeatKillException("repeat submit");
			//ִ����ɱ�߼�
			Date nowTime = new Date();
			//�����
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			//û�м��������ɱ��������ʱ�䲻����ɱ��Χ��
			if(updateCount<=0)
			{
				throw new SeckillCloseException("sekcill has closed");
			}
			else
			{
				int insertCount = successSeckillDao.insertSuccessSeckill(seckillId, userPhone);
				if(insertCount<=0){
					//��¼�ظ�
					throw new RepeatKillException("�ظ��ύ");
				}else {
					//��ɱ�ɹ�
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
	
	//��ȡmd5  ��slat������ ��ֹ���������
	private String getMd5(long seckillId)
	{
		String base = seckillId +"/" +slat;
		
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
