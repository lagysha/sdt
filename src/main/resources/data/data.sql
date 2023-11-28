insert into departments(name) values('Back-end');
insert into employees(login_name, password, role, amount, department_id) values('john','1234','employee',30000,1);
insert into employees(login_name, password, role, amount, department_id) values('doe','123456789','headOfDepartment',100000,1);
update departments set head_of_department__id = 2 where id = 1;