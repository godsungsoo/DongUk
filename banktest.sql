insert all 
into bankmanager values (SEQ_NO.NEXTVAL, ?, ?, ?) 
into account  values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT) 
into transaction values (SEQ_NO.CURRVAL,'02-'||SEQ_ACC.CURRVAL,DEFAULT,1,' ',?,DEFAULT,?) 
select * from dual;

insert all into bankmanager values ((select user_no from bankmanager where user_name = 'won' and user_ssn = '910511-1020304'), ?, ?, (select phone from bankmanager where user_name = 'won' and user_ssn = '910511-1020304')) into account  values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT) into transaction values ((select user_no from bankmanager where user_name = 'won' and user_ssn = '910511-1020304'),'02-'||SEQ_ACC.CURRVAL,DEFAULT,1,' ',?,DEFAULT,?) select * from dual;


insert all 
into bankmanager values ((select user_no 
                          from bankmanager 
                          where user_name = 'won' and user_ssn = '910511-1020304'), ?, ?, (select phone from bankmanager where user_name = 'won' and user_ssn = '910511-1020304')) 
into account  values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT) 
into transaction values ((select user_no from bankmanager where user_name = 'won' and user_ssn = '910511-1020304'),'02-'||SEQ_ACC.CURRVAL,DEFAULT,1,' ',?,DEFAULT,?) 
select * from dual;

insert all 
into account  values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT) 
into transaction values ((select user_no from bankmanager where user_name = 'won' and user_ssn = '910511-1020304'), '02-'||SEQ_ACC.CURRVAL,DEFAULT,1,' ',1000,DEFAULT,1000) 
select * from dual;

--자바에 적용할 쿼리문
insert all into account  values ('02-'||SEQ_ACC.NEXTVAL, DEFAULT) into transaction values ((select user_no from bankmanager where user_name = 'won' and user_ssn = '910511-1020304'), '02-'||SEQ_ACC.CURRVAL,DEFAULT,1,' ',?,DEFAULT,?) select * from dual;

(select user_no
from bankmanager
where user_name = 'won' and user_ssn = '910511-1020304');

