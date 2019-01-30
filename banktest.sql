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


insert into transaction values(?,?,?,?,?,?,?);
--계좌번호, 입금액을 입력받음
select distinct user_no
from transaction
where account_no = '02-106';

insert into transaction values((select distinct user_no
                                from transaction
                                where account_no = ?),?,default,1,' ',?,0,?+(select balance
from (select balance, trans_date, rank() over(order by trans_date desc) as rank
      from transaction
      where account_no = '02-106') 
where rank = 1));

select * from transaction;
         
select min(trans_date)
from transaction
where account_no = '02-106'
group by trans_date;
select to_char(trans_date,'RRRR/MM/DD HH:MI:SS') from transaction order by 1 desc;

select min(to_char(trans_date,'RRRR/MM/DD HH:MI:SS')), balance
from transaction
where account_no = '02-106'
group by balance;

select * from transaction where account_no = '02-106';
select balance
from (select balance, rank() over(order by to_char(trans_date,'RRRR/MM/DD HH:MI:SS') desc) as rank
      from transaction
      where account_no = '02-106');
      
      select balance,to_char(trans_date,'RRRR/MM/DD HH:MI:SS'), rank() over(order by to_char(trans_date,'RRRR/MM/DD HH:MI:SS') desc) as rank
      from transaction
      where account_no = '02-106';
select balance,to_char(trans_date,'RRRR/MM/DD HH:MI:SS'), rank() over(order by to_char(trans_date,'RRRR/MM/DD HH:MI:SS') desc) as rank
      from transaction;
      
      
select balance
from (select balance, trans_date, rank() over(order by trans_date desc) as rank
      from transaction
      where account_no = '02-106') 
where rank = 1;
      

insert all

into transaction values((select distinct user_no
                                from transaction
                                where account_no = '02-102'),'02-102',default,2,' ',0,1000,(select balance
from (select balance, trans_date, row_number() over(order by trans_date desc) as rank
      from transaction
      where account_no = '02-102') 
where rank = 1)-1000)
into transaction values((select distinct user_no
                                from transaction
                                where account_no = '02-106'),'02-106',default,1,' ',1000,0,1000+(select balance
from (select balance, trans_date, row_number() over(order by trans_date desc) as rank
      from transaction
      where account_no = '02-106') 
where rank = 1))
select * from dual;


