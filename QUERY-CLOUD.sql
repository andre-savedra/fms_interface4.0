use fms_interface;

/*DROP DATABASE fms_interface;*/

/*select *from tb_user;
update tb_user set phone=19989395707 where id=38738276895;*/

SELECT *FROM tb_order_type;

select *from tb_order;


delete from tb_order where id >0;

update tb_order set produced = 1, units_produced = 1, output_date = now() where id > 0;

select *from tb_turn_variables;
select *from tb_milling_variables;
select *from tb_robot_variables;
select *from tb_supervisory_exchange;
select *from tb_machinery;
select *from tb_magazine;


select *from tb_delivery;
update tb_delivery set order_to_delivery = 0, not_exist = 0 where id = 1;

update tb_magazine set order_id = 0, located = 0, order_subindex = 0, last_machine_step = 0 where id > 0;

update tb_machinery set flex = 0, has_job = 0, info = "", job_ended = 0 , step_id=0, job_accepted = 0, job_name = "", order_id = 0, permission_to_start = 0, machine_ready = 0  where id > 0;
update tb_machinery set permission_to_start = 0 where id >= 0;

select *from tb_machinery;

select * from tb_step_part;

select * from tb_step_part where id=38;
select * from tb_milling_variables;
select * from tb_turn_variables;

update tb_step_part set gcode = "O7101 \n N200 M30" where id=38;

describe tb_turn_variables;
describe tb_milling_variables;
describe tb_robot_variables;
describe tb_supervisory_exchange;
describe tb_manut_variables;
describe tb_magazine;

select *from tb_manut_variables;



update tb_manut_variables set counter_clamping_max = 0, counter_part_max = 0, counter_port_max = 0, hours_machining_max = 0 where id >= 1;

insert into tb_manut_variables (machine_id, counter_clamping, counter_part, counter_port, hours_machining, last_update, 
								counter_clamping_max, counter_part_max, counter_port_max, hours_machining_max) values (
	3, 0, 0, 0, 0.0, now(), 0,0,0,0
);

insert into tb_manut_variables (machine_id, counter_clamping, counter_part, counter_port, hours_machining, last_update,
								counter_clamping_max, counter_part_max, counter_port_max, hours_machining_max) values (
	4, 0, 0, 0, 0.0, now(), 0,0,0,0 
);

insert into tb_manut_variables (machine_id, counter_clamping, counter_part, counter_port, hours_machining, last_update,
								counter_clamping_max, counter_part_max, counter_port_max, hours_machining_max) values (
	5, 0, 0, 0, 0.0, now(), 0,0,0,0
);

describe tb_order;
select *from tb_order;
update tb_order set produced = 1 where id > 0;


/*drop table tb_turn_variables;
drop table tb_milling_variables;
drop table tb_robot_variables;
drop table tb_supervisory_exchange;*/

INSERT into tb_turn_variables (counter_turn_part, turn_machining, actual_date, status_alarm_turn) values (10,1,now(),0);
SELECT *from tb_turn_variables where id = (select max(id) from tb_turn_variables);

INSERT into tb_milling_variables (counter_mill_part, mill_machining, actual_date, status_alarm_mill) values (10,0,now(),0);

delete from tb_turn_variables where id <300;

UPDATE tb_order SET produced = true where id >0;

select *from tb_order;
select *from tb_user;

select gcode_mill_loaded from tb_supervisory_exchange where id = 1;
select gcode_turn_loaded from tb_supervisory_exchange where id = 1;

update tb_supervisory_exchange  SET gcode_turn_loaded = 0 WHERE ID = 1;
select *from tb_supervisory_exchange;




