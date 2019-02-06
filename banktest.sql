--delete (select * 
--from account
--where account_no = (select distinct account_no
--                    from transaction
--                    join bankmanager using(user_no)
--                    where user_name = '윤선용' and user_ssn = '911201-1000000' and account_no = '02-106'));

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


insert all into transaction values((select distinct user_no from transaction where account_no = '02-102'),'02-102',default,2,' ',0,1000,(select balance from (select balance, trans_date, row_number() over(order by trans_date desc) as rank from transaction where account_no = '02-102')where rank = 1)-1000) into transaction values((select distinct user_no from transaction where account_no = '02-106'),'02-106',default,1,' ',1000,0,1000+(select balance from (select balance, trans_date, row_number() over(order by trans_date desc) as rank from transaction where account_no = '02-106') where rank = 1)) select * from dual;

--자바 계좌이체에 적용할 쿼리문
insert all
into transaction values((select distinct user_no
                                from transaction
                                where account_no = '02-102'),'02-102',default,3,(select distinct user_name
from transaction
join bankmanager using (user_no)
where account_no = '02-106'),0,1000,(select balance
from (select balance, trans_date, row_number() over(order by trans_date desc) as rank
      from transaction
      where account_no = '02-102') 
where rank = 1)-1000)
into transaction values((select distinct user_no
                                from transaction
                                where account_no = '02-106'),'02-106',default,3,(select distinct user_name
from transaction
join bankmanager using (user_no)
where account_no = '02-102'),1000,0,1000+(select balance
from (select balance, trans_date, row_number() over(order by trans_date desc) as rank
      from transaction
      where account_no = '02-106') 
where rank = 1))
select * from dual;

insert all
into transaction values((select distinct user_no
                                from transaction
                                where account_no = '02-102'),'02-102',default,3,(select distinct user_name
                                                                                from transaction
                                                                                join bankmanager using (user_no)
                                                                                where account_no = '02-106'),0,1000,(select balance
                                                                                                                    from (select balance, trans_date, row_number() over(order by trans_date desc) as rank
                                                                                                                         from transaction
                                                                                                                        where account_no = '02-102') where rank = 1)-1000)
into transaction values((select distinct user_no
                                from transaction
                                where account_no = '02-106'),'02-106',default,3,(select distinct user_name
                                                                                from transaction
                                                                                  join bankmanager using (user_no)
                                                                                where account_no = '02-102'),1000,0,1000+(select balance
                                                                                                                         from (select balance, trans_date, row_number() over(order by trans_date desc) as rank
                                                                                                                         from transaction
                                                                                                                        where account_no = '02-106') where rank = 1))
select * from dual;

select distinct user_name
from transaction
join bankmanager using (user_no)
where account_no = '02-106';

select user_no, user_name, account_no, balance, open_date, trans_date, phone from bankmanager join transaction using (user_no) join account using (account_no);

select user_no, user_name, account_no, balance, open_date, trans_date, phone 
from bankmanager 
join transaction using (user_no) 
join account using (account_no);

select user_no, user_name, account_no, balance, open_date, max(trans_date), phone 
from bankmanager 
join transaction using (user_no) 
join account using (account_no)
group by user_no;


select *
from bankmanager 
join transaction using (user_no) 
join account using (account_no);

select *
from 

select user_no
from bankmanager;

select *
from bankmanager
join transaction using(user_no)
join account using(account_no);

--고객전체정보 : 계좌번호 별로 최근의 balance, 최종거래일 나오게
select user_no, user_name, account_no, balance, open_date, trans_date, phone, to_char(trans_date,'RR/MM/DD HH:MI:SS')
from bankmanager
join transaction using(user_no)
join account using(account_no)
where trans_date in (select max(trans_date)
                    from transaction
                    group by account_no);
                    
select user_no, user_name, account_no, balance, open_date, trans_date, phone, to_char(trans_date,'RR/MM/DD HH:MI:SS') from bankmanager join transaction using(user_no) join account using(account_no) where trans_date in (select max(trans_date) from transaction group by account_no);

--원래 selectAll
select user_no,user_name, account_no, balance, open_date, trans_date, phone from bankmanager join transaction using (user_no) join account using (account_no);

SELECT USER_NO, USER_NAME, ACCOUNT_NO, BALANCE, OPEN_DATE, TRANS_DATE, PHONE 
FROM BANKMANAGER 
JOIN TRANSACTION USING (USER_NO) 
JOIN ACCOUNT USING (ACCOUNT_NO)
WHERE USER_NAME like '%김%' and trans_date in (select max(trans_date) from transaction group by account_no);

