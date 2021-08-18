insert into user values(1,'$2a$10$VMiHu47HkDLoTk4aLJ9KXev4si9tiekCjVtMCtLvVjFxC7yX0yJQe' ,'1111111111', 'ROLE_ADMIN', 'vasile');
insert into user values(2,'$2a$10$VMiHu47HkDLoTk4aLJ9KXev4si9tiekCjVtMCtLvVjFxC7yX0yJQe' ,'2222222222', 'ROLE_USER', 'cornel');
insert into user values(3,'$2a$10$VMiHu47HkDLoTk4aLJ9KXev4si9tiekCjVtMCtLvVjFxC7yX0yJQe' ,'3333333333', 'ROLE_ADMIN', 'mirel');
insert into user values(4,'$2a$10$VMiHu47HkDLoTk4aLJ9KXev4si9tiekCjVtMCtLvVjFxC7yX0yJQe' ,'4444444444', 'ROLE_USER', 'costel');

insert into account values(1,'1111-1111-1111-1111', 'CURRENCY_EURO','353','11/25',100,2);
insert into account values(2,'2222-2222-2222-2222', 'CURRENCY_EURO','359','12/25',100,2);
insert into account values(5,'3333-3333-3333-3333', 'CURRENCY_RON','352','10/21',100,2);
insert into account values(3,'4444-4444-4444-4445','CURRENCY_RON','354','11/25',100,4);
insert into account values(4,'4444-4444-4344-4445','CURRENCY_RON','359','1/25',100,4);
insert into account values(6,'4444-4444-4644-4445','CURRENCY_RON','351','1/24',100,2);


insert into notes values(1,'RON_1', 1);
insert into notes values(2,'RON_5', 5);
insert into notes values(3,'RON_10', 10);
insert into notes values(4,'RON_50', 50);
insert into notes values(5,'RON_100', 100);
insert into notes values(6,'RON_200', 200);
insert into notes values(7,'RON_500', 500);

insert into notes values(8,'EURO_5', 5);
insert into notes values(9,'EURO_10', 10);
insert into notes values(10,'EURO_20', 20);
insert into notes values(11,'EURO_50', 50);
insert into notes values(12,'EURO_100', 100);
insert into notes values(13,'EURO_200',200);
insert into notes values(14,'EURO_500', 500);

insert into drawer values(1);
insert into money values(1, 'CURRENCY_RON');
insert into money values(2, 'CURRENCY_EURO');

insert into drawer_moneys values(1, 1);
insert into drawer_moneys values(1, 2);

insert into transaction  value (1, 300, '2021-03-19 12:19:15' ,'TRANSACTION_DEPOSIT');
insert into transaction  value (2, 500, '2021-06-19 1:19:15' ,'TRANSACTION_WITHDRAW');
insert into transaction  value (3, 600, '2021-07-19 10:19:15' ,'TRANSACTION_WITHDRAW');
insert into transaction  value (4, 700, '2021-08-19 12:19:15' ,'TRANSACTION_DEPOSIT');
insert into transaction  value (5, 900, '2021-03-19 11:19:15' ,'TRANSACTION_DEPOSIT');

insert into transaction  value (6, 3000, '2021-03-19 12:19:15' ,'TRANSACTION_DEPOSIT');
insert into transaction  value (7, 5000, '2021-06-19 1:19:15' ,'TRANSACTION_WITHDRAW');
insert into transaction  value (8, 6000, '2021-07-19 10:19:15' ,'TRANSACTION_WITHDRAW');
insert into transaction  value (9, 7000, '2021-08-19 12:19:15' ,'TRANSACTION_DEPOSIT');
insert into transaction  value (10, 9000, '2021-03-19 11:19:15' ,'TRANSACTION_DEPOSIT');

insert into transaction  value (11, 30, '2021-03-19 12:19:15' ,'TRANSACTION_DEPOSIT');
insert into transaction  value (12, 50, '2021-06-19 1:19:15' ,'TRANSACTION_WITHDRAW');
insert into transaction  value (13, 60, '2021-07-19 10:19:15' ,'TRANSACTION_WITHDRAW');
insert into transaction  value (14, 70, '2021-08-19 12:19:15' ,'TRANSACTION_DEPOSIT');
insert into transaction  value (15, 90, '2021-03-19 11:19:15' ,'TRANSACTION_DEPOSIT');

insert into account_transactions value(1,1);
insert into account_transactions value(1,2);
insert into account_transactions value(1,3);
insert into account_transactions value(1,4);
insert into account_transactions value(1,5);

insert into account_transactions value(2,6);
insert into account_transactions value(2,7);
insert into account_transactions value(2,8);
insert into account_transactions value(2,9);
insert into account_transactions value(2,10);

insert into account_transactions value(5,11);
insert into account_transactions value(5,12);
insert into account_transactions value(5,13);
insert into account_transactions value(5,14);
insert into account_transactions value(5,15);


