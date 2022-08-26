create table animal(
idx number
,category varchar(30)
,name varchar(30)
,rank number
,primary key(idx)
);

create sequence seq_animal
increment by 1
start with 1;

insert into animal(idx,category,name,rank) values(seq_animal.nextval,'조류','참새',5);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'포유류','고양이',3);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'파충류','뱀',9);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'어류','상어',12);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'포유류','치타',2);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'양서류','개구리',8);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'파충류','도마뱀',4);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'곤충','메뚜기',6);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'양서류','맹꽁이',10);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'조류','독수리',1);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'어류','참치',11);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'포유류','고래',7);

COMMIT;

--랭크를 기준으로 오름차순
SELECT * FROM ANIMAL a ORDER BY RANK ASC;

--카테고리별로 구분해서 보이게 하기
SELECT * FROM ANIMAL ORDER BY CATEGORY asc;

--현재 몰려다닌 상태에서 rank가 더 높은애가 위로
SELECT * FROM ANIMAL a ORDER BY CATEGORY ASC, RANK ASC ;

CREATE TABLE reboard(
	reboard_id NUMBER PRIMARY KEY,
	title varchar(100),
	writer varchar(20),
	content clob,
	regdate DATE DEFAULT sysdate,
	hit NUMBER DEFAULT 0,
	team NUMBER DEFAULT 0,
	step NUMBER DEFAULT 0,
	DEPTH NUMBER DEFAULT 0
	
);

create sequence seq_reboard
increment by 1
start with 1;

SELECT * FROM REBOARD ;