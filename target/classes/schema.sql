CREATE DATABASE seckill;
USE seckill;
CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '�����Ʒid',
`name` varchar(120) NOT NULL COMMENT '��Ʒ����',
`number` int NOT NULL COMMENT '�������',
`start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '��ɱ��ʼʱ��',
`end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '��ɱ����ʱ��',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT ='��ɱ���ݿ�';

insert into 
	seckill(name,number,start_time,end_time)
values
('5����ɱiphone7',100,'2017-05-01 00:00:00','2017-05-02 00:00:00'),
('3����ɱnike',50,'2017-05-01 00:00:00','2017-05-02 00:00:00'),
('1����ɱMI5',200,'2017-05-01 00:00:00','2017-05-02 00:00:00');


--��ɱ�ɹ���ϸ��
--�û������Ϣ
CREATE TABLE success_seckill(
`seckill_id` bigint NOT NULL  COMMENT '��ɱ��Ʒid',
`user_phone` bigint NOT NULL COMMENT '�û��ֻ�����',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '����״̬��-1��ʾ��Ч��0��ʾ�ɹ���1��ʾ�Ѹ���',
`create_time` timestamp NOT NULL COMMENT '����ʱ��',
PRIMARY KEY (seckill_id,user_phone),/*�����������Է�ֹ�ظ���ɱ */
key idx_create_time(create_time)

)ENGINE=InnoDB  DEFAULT CHARSET=UTF8 COMMENT ='��ɱ��ϸ��';




