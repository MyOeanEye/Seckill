CREATE DATABASE seckill;
USE seckill;
CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '库存商品id',
`name` varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
`end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT ='秒杀数据库';

insert into 
	seckill(name,number,start_time,end_time)
values
('5折秒杀iphone7',100,'2017-05-01 00:00:00','2017-05-02 00:00:00'),
('3折秒杀nike',50,'2017-05-01 00:00:00','2017-05-02 00:00:00'),
('1折秒杀MI5',200,'2017-05-01 00:00:00','2017-05-02 00:00:00');


--秒杀成功明细表
--用户相关信息
CREATE TABLE success_seckill(
`seckill_id` bigint NOT NULL  COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号码',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '订单状态：-1表示无效，0表示成功，1表示已付款',
`create_time` timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id,user_phone),/*联合主键，以防止重复秒杀 */
key idx_create_time(create_time)

)ENGINE=InnoDB  DEFAULT CHARSET=UTF8 COMMENT ='秒杀明细表';




