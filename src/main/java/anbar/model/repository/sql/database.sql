create table products(
                         id number primary key ,
                         brand nvarchar2(10),
                         model nvarchar2(20),
                         os nvarchar2(10),
                         has_charger number(1),
                         has_headset number(1),
                         price number,
                         count number,
                         manufacture_date date

);

create sequence products_seq start with 1 increment by 1;



----storekepper
create table storekeppers (
                              id number primary key,
                              nationalid nvarchar2(10) not null ,
                              name nvarchar2(30),
                              family nvarchar2(30),
                              gender nvarchar2(8),
                              birth_date date,
                              phone_number nvarchar2(11),
                              username nvarchar2(20) not null ,
                              password nvarchar2(30) not null

);

create sequence storekeppers_seq start with 1 increment by 1;



--storekeppers_products
create table transaction(
                            id int primary key ,
                            product_id references products,
                            storekeppers_id references storekeppers,
                            transaction_type nvarchar2(10),
                            quantity number,
                            transaction_date date DEFAULT sysdate
);

create sequence transaction_seq start with 1 increment by 1;


----report transaction
create view transaction_report as
select t.id           as transaction_id,
       t.product_id,
       p.brand        as product_brand,
       p.model        as product_model,
       p.os           as product_os,
       p.has_charger  as product_has_charger,
       p.has_headset  as product_has_headset,
       p.price        as product_price,
       p.count        as product_count,
       t.storekeppers_id,
       s.nationalid   as storekeppers_natiobalid,
       s.name         as storekeppers_name,
       s.family       as storekeppers_family,
       s.gender       as storekeppers_gender,
       s.birth_date   as storekeppers_birth_date,
       s.phone_number as storekeppers_phone_number,
       s.username     as storekeppers_username,
       s.password     as storekeppers_password,

       t.transaction_type,
       t.quantity,
       t.transaction_date

from transaction  t  -- برای جداول اسم مستعار as ندارد
join products p on t.product_id = p.id
join storekeppers s on t.storekeppers_id = s.id;



select * from transaction_report;




