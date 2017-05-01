package io.fu.dto;

import io.fu.entity.SuccessSeckill;
import io.fu.enums.SeckillState;

//封装秒杀执行结果
public class SeckillExecution {

	private long seckillId;
	
	//秒杀执行状态
	private int state;
	//状态信息
	private String stateInfo;
	//秒杀成功对象
	private SuccessSeckill successSeckill;
	
	
	//如果成功返回所有信息
	public SeckillExecution(long seckillId, SeckillState state, SuccessSeckill successSeckill) {
		super();
		this.seckillId = seckillId;
		this.state = state.getState();
		this.stateInfo = state.getStateInfo();
		this.successSeckill = successSeckill;
	}
	
	//失败则返回一部分信息
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
