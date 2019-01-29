DROP TABLE ACCOUNT CASCADE constraints;
DROP table BANKMANAGER CASCADE constraints;
DROP table transaction CASCADE constraints;
DROP TABLE TYPE CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_NO;
DROP SEQUENCE SEQ_ACC;

CREATE TABLE BANKMANAGER(
    USER_NO NUMBER,
    USER_NAME VARCHAR2(20),
    USER_SSN VARCHAR2(14) unique,
    PHONE VARCHAR2(20)
);

COMMENT ON COLUMN BANKMANAGER.USER_NO IS '고객번호';
COMMENT ON COLUMN BANKMANAGER.USER_NAME IS '고객명';
COMMENT ON COLUMN BANKMANAGER.USER_SSN IS '고객주민번호';
COMMENT ON COLUMN BANKMANAGER.PHONE IS '핸드폰번호';

ALTER TABLE BANKMANAGER
    ADD CONSTRAINT PKUSER_NO PRIMARY KEY (USER_NO);

CREATE TABLE TYPE(
	TYPE_NO NUMBER,
	TYPE_NAME VARCHAR2(15)
);

COMMENT ON COLUMN TYPE.TYPE_NO IS '거래종류';
COMMENT ON COLUMN TYPE.TYPE_NAME IS '거래이름';

ALTER TABLE TYPE
    ADD CONSTRAINT PKTYPE_NO PRIMARY KEY (TYPE_NO);

CREATE TABLE ACCOUNT(
	ACCOUNT_NO VARCHAR2(30),
	OPEN_DATE DATE DEFAULT SYSDATE
);

COMMENT ON COLUMN ACCOUNT.ACCOUNT_NO IS '계좌번호';
COMMENT ON COLUMN ACCOUNT.OPEN_DATE IS '통장개설일자';

ALTER TABLE ACCOUNT
    ADD CONSTRAINT PKACCOUNT_NO PRIMARY KEY (ACCOUNT_NO);


CREATE TABLE TRANSACTION(
    USER_NO NUMBER NOT NULL,
	ACCOUNT_NO VARCHAR2(30) NOT NULL,
    TRANS_DATE DATE DEFAULT SYSDATE,
    TYPE_NO NUMBER,
	TRANS_CONTENT VARCHAR2(30),
   	DEPOSIT NUMBER check (DEPOSIT >= 0),
	WITHDRAW NUMBER check (WITHDRAW >= 0),
	BALANCE NUMBER check (BALANCE >= 0)    
); 
    
COMMENT ON COLUMN TRANSACTION.USER_NO IS '고객번호';
COMMENT ON COLUMN TRANSACTION.ACCOUNT_NO IS '계좌번호';
COMMENT ON COLUMN TRANSACTION.TRANS_DATE IS '거래일자';
COMMENT ON COLUMN TRANSACTION.TYPE_NO IS '거래종류';
COMMENT ON COLUMN TRANSACTION.TRANS_CONTENT IS '거래내용';
COMMENT ON COLUMN TRANSACTION.DEPOSIT IS '입금액';
COMMENT ON COLUMN TRANSACTION.WITHDRAW IS '출금액';
COMMENT ON COLUMN TRANSACTION.BALANCE IS '잔액';

ALTER TABLE TRANSACTION
    ADD CONSTRAINT PKUACCOUNT PRIMARY KEY (USER_NO, ACCOUNT_NO,TRANS_DATE,BALANCE);
ALTER TABLE TRANSACTION
    ADD CONSTRAINT FKUSER_NO FOREIGN KEY (USER_NO) REFERENCES BANKMANAGER (USER_NO) ON DELETE CASCADE;
ALTER TABLE TRANSACTION
    ADD CONSTRAINT FKTACCOUNT_NO FOREIGN KEY (ACCOUNT_NO) REFERENCES ACCOUNT (ACCOUNT_NO) ON DELETE CASCADE;
ALTER TABLE TRANSACTION
    ADD CONSTRAINT FKTYPE_NO FOREIGN KEY (TYPE_NO) REFERENCES TYPE; 

CREATE SEQUENCE SEQ_NO
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

INSERT INTO BANKMANAGER VALUES(SEQ_NO.NEXTVAL, '김동욱', '910618-1000000','010-1111-1111');
INSERT INTO BANKMANAGER VALUES(SEQ_NO.NEXTVAL, '윤선용', '911201-1000000','010-2222-2222');
INSERT INTO BANKMANAGER VALUES(SEQ_NO.NEXTVAL, '김성수', '900112-1000000','010-3333-3333');
INSERT INTO BANKMANAGER VALUES(SEQ_NO.NEXTVAL, '안정은', '920610-2000000','010-4444-4444');
INSERT INTO BANKMANAGER VALUES(SEQ_NO.NEXTVAL, '윤석호', '930718-1000000','010-5555-5555');
INSERT INTO BANKMANAGER VALUES(SEQ_NO.NEXTVAL, '정민경', '910617-2100000','010-6666-6666');

CREATE SEQUENCE SEQ_ACC
START WITH 100
INCREMENT BY 1
NOCYCLE
NOCACHE;

insert into ACCOUNT VALUES('02-'||SEQ_ACC.NEXTVAL,DEFAULT);
insert into ACCOUNT VALUES('02-'||SEQ_ACC.NEXTVAL,DEFAULT);
insert into ACCOUNT VALUES('02-'||SEQ_ACC.NEXTVAL,DEFAULT);
insert into ACCOUNT VALUES('02-'||SEQ_ACC.NEXTVAL,DEFAULT);
insert into ACCOUNT VALUES('02-'||SEQ_ACC.NEXTVAL,DEFAULT);
insert into ACCOUNT VALUES('02-'||SEQ_ACC.NEXTVAL,DEFAULT);
insert into ACCOUNT VALUES('02-'||SEQ_ACC.NEXTVAL,DEFAULT);

INSERT INTO TYPE VALUES (1,'입금');
INSERT INTO TYPE VALUES (2,'출금');
INSERT INTO TYPE VALUES (3,'이체');

INSERT INTO TRANSACTION VALUES(1,'02-100', DEFAULT, 1, ' ',1000,0,1000);
INSERT INTO TRANSACTION VALUES(1,'02-101', DEFAULT, 1, ' ',1000,0,1000);
INSERT INTO TRANSACTION VALUES(1,'02-102', DEFAULT, 1, ' ',1000,0,1000);
INSERT INTO TRANSACTION VALUES(2,'02-103', DEFAULT, 1, ' ',1000,0,1000);
INSERT INTO TRANSACTION VALUES(2,'02-104', DEFAULT, 1, ' ',1000,0,1000);
INSERT INTO TRANSACTION VALUES(2,'02-105', DEFAULT, 1, ' ',1000,0,1000);
INSERT INTO TRANSACTION VALUES(2,'02-106', DEFAULT, 1, ' ',1000,0,1000);
INSERT INTO TRANSACTION VALUES(2,'02-106', DEFAULT, 1, ' ',1000,0,2000);

insert into transaction values((select distinct user_no from transaction where account_no = '02-106'),'02-106',DEFAULT, 1, ' ',1000,0,3000);




select user_no, user_name, account_no, balance, open_date, trans_date, phone from bankmanager join transaction using (user_no) join account using (account_no);
/*고객번호 user_no
이름 user_name
계좌번호 account_no
잔액 balance
통장개설일 open_date
최근거래일 trans_date
핸드폰 번호 phone*/
--select a.user_no from bankmanager a, transaction b where a.user_no = b.user_no;
--
--select user_no, account_no, trans_date, type_no, trans_content, deposit, withdraw, balance from transaction where account_no = '02-100';
--
--select * from bankmanager;
--update bankmanager set phone = '010' where user_no = 1;
--rollback;
--select * from transaction;
--
--delete 
--from bankmanager 
----join transaction using (user_no)
--where user_name = '김동욱' and user_ssn = '910618-1000000';-- and account_no = '02_100';
--rollback;
--delete
--from transaction
--join bankmanager using(user_no)
--where user_name = '김동욱' and user_ssn = '910618-1000000' and account_no = '02-100';
--
--select *
--from transaction
--join bankmanager using(user_no)
--where user_name = '김동욱' and user_ssn = '910618-1000000' and account_no = '02-101';
--select *
--from transaction; 
--where account_no like '02-100';
--select * from transaction
--where account_no = '02-100';
--
--delete (select *
--from transaction
--join bankmanager using(user_no)
--join account using(account_no)
--where user_name = '윤선용' and user_ssn = '911201-1000000' and account_no = '02-106');
--delete account where account_no = '02-106';
--rollback;
--select * from transaction; 
--select * from bankmanager;
--select * from account;
--select * from transaction 
--join bankmanager using(user_no)
--where user_name = '윤선용' and account_no = '02-106';
--
--select *
--from account
--join transaction using (account_no)
--join bankmanager using(user_no)
--where user_name = '윤선용' and user_ssn = '911201-1000000' and account_no = '02-106';
--delete (select *
--from account
--join transaction using (account_no)
--join bankmanager using(user_no)
--where user_name = '윤선용' and user_ssn = '911201-1000000' and account_no = '02-106');
--
--select * 
--from account
--where account_no = (select distinct account_no
--                    from transaction
--                    join bankmanager using(user_no)
--                    where user_name = '윤선용' and user_ssn = '911201-1000000' and account_no = '02-106');
--delete (select * 
--from account
--where account_no = (select distinct account_no
--                    from transaction
--                    join bankmanager using(user_no)
--                    where user_name = '윤선용' and user_ssn = '911201-1000000' and account_no = '02-106'));