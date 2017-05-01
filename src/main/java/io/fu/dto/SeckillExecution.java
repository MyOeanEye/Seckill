package io.fu.dto;

import io.fu.entity.SuccessSeckill;
import io.fu.enums.SeckillState;

//��װ��ɱִ�н��
public class SeckillExecution {

	private long seckillId;
	
	//��ɱִ��״̬
	private int state;
	//״̬��Ϣ
	private String stateInfo;
	//��ɱ�ɹ�����
	private SuccessSeckill successSeckill;
	
	
	//����ɹ�����������Ϣ
	public SeckillExecution(long seckillId, SeckillState state, SuccessSeckill successSeckill) {
		super();
		this.seckillId = seckillId;
		this.state = state.getState();
		this.stateInfo = state.getStateInfo();
		this.successSeckill = successSeckill;
	}
	
	//ʧ���򷵻�һ������Ϣ
	public SeckillExecution(long seckillId, SeckillState state) {
		super();
		this.seckillId = seckillId;
		this.state = state.getState();
		this.stateInfo = state.getStateInfo();
	}


	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public SuccessSeckill getSuccessSeckill() {
		return successSeckill;
	}
	public void setSuccessSeckill(SuccessSeckill successSeckill) {
		this.successSeckill = successSeckill;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successSeckill=" + successSeckill + "]";
	}
	
}
