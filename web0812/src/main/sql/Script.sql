CREATE  TABLE news(
	news_id NUMBER PRIMARY KEY,
	title varchar(100),
	writer varchar(20),
	content clob,
	regdate DATE DEFAULT sysdate,
	hit NUMBER DEFAULT 0
);

CREATE TABLE comments(
	comments_id NUMBER PRIMARY KEY,
	detail varchar(1000),
	writedate DATE DEFAULT sysdate,
	author varchar(20),
	news_id NUMBER --FOREIGN KEY
	,CONSTRAINT fk_news_comments FOREIGN KEY (news_id) REFERENCES news(news_id)
	
);

CREATE SEQUENCE seq_news
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE seq_comments
INCREMENT BY 1
START WITH 1;



--뉴스와 댓글 테이블을 합치되 일반 join (innerJOIN)으로 합치기
SELECT * 
FROM NEWS n, COMMENTS c 
WHERE n.NEWS_ID =c.NEWS_ID ;

--outerJOIN으로 합치기
SELECT news_id, title ,writer ,regdate ,hit, COUNT(news_id) as cnt 
FROM(
	SELECT title ,writer ,regdate ,hit, c.news_id  AS news_id 
	FROM news n LEFT OUTER JOIN  comments c 
	ON n.news_id =c.news_id
	) GROUP BY news_id, title ,writer, regdate ,hit

--원본 news는 무조건 모든 내용이 나와야함



SELECT news_id, title ,writer ,regdate ,hit, COUNT(nid) as cnt FROM(SELECT n.news_id AS news_id,title ,writer ,regdate ,hit, c.news_id  AS nid FROM news n LEFT OUTER JOIN  comments c ON n.news_id =c.news_id) GROUP BY news_id, title ,writer, regdate ,hit