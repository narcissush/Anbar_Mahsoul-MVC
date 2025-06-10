create table products
(
    id               number primary key,
    title            nvarchar2(30),
    brand            nvarchar2(10),
    model            nvarchar2(20),
    os               nvarchar2(10),
    has_charger      number(1),
    has_headset      number(1),
    price            number,
    count            number,
    manufacture_date date

);

create sequence products_seq start with 1 increment by 1;


----storekeeper
create table storekeepers
(
    id           number primary key,
    national_id  nvarchar2(10) not null,
    name         nvarchar2(30),
    family       nvarchar2(30),
    gender       nvarchar2(8),
    birth_date   date,
    phone_number nvarchar2(11),
    username     nvarchar2(20) not null,
    password     nvarchar2(30) not null

);

create sequence storekeepers_seq start with 1 increment by 1;


--storekeepers_products
create table transactions
(
    id               int primary key,
    products_id references products,
    storekeepers_id references storekeepers,
    transaction_type nvarchar2(10),
    quantity         number,
    transaction_date timestamp DEFAULT sysdate
);

create sequence transactions_seq start with 1 increment by 1;


----report transaction
create view transactions_report as
select t.id               as transaction_id,
       t.products_id,
       p.title            as products_title,
       p.brand            as products_brand,
       p.model            as products_model,
       p.os               as products_os,
       p.has_charger      as products_has_charger,
       p.has_headset      as products_has_headset,
       p.price            as products_price,
       p.count            as products_count,
       p.manufacture_date as products_manufacture_date,
       t.storekeepers_id,
       s.national_id      as storekeepers_national_id,
       s.name             as storekeepers_name,
       s.family           as storekeepers_family,
       s.gender           as storekeepers_gender,
       s.birth_date       as storekeepers_birth_date,
       s.phone_number     as storekeepers_phone_number,
       s.username         as storekeepers_username,
       s.password         as storekeepers_password,

       t.transaction_type,
       t.quantity,
       t.transaction_date

from TRANSACTIONS t -- برای جداول اسم مستعار as ندارد
         join products p on t.products_id = p.id
         join storekeepers s on t.storekeepers_id = s.id;



select *
from transactions_report;

select *
from storekeepers;
select *
from products;

select *
from Products
where price between 9000 and 12000;




