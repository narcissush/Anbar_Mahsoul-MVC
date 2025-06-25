create table products
(
    id            number primary key,
    category      nvarchar2(30),
    brand         nvarchar2(10),
    model         nvarchar2(20),
    os            nvarchar2(10),
    has_charger   number(1),
    has_headset   number(1),
    serial_number nvarchar2(20),
    price         number,
    quantity         number

);

create sequence products_seq start with 1 increment by 1;


----suppliers
create table suppliers
(
    id            number primary key,
    name nvarchar2(30),
    person_type   nvarchar2(10)  ,
    national_id   nvarchar2(10) not null,
    postalcode    nvarchar2(10),
    phone_number  nvarchar2(15),
    mobile_number nvarchar2(15)

);

create sequence suppliers_seq start with 1 increment by 1;



create table users
(
    id          number primary key,
    national_id nvarchar2(10) unique,
    name        nvarchar2(30),
    family      nvarchar2(30),
    gender      nvarchar2(5),
    birth_date  date,
    username    nvarchar2(20) not null,
    password    nvarchar2(30) not null
);

create sequence users_seq start with 1 increment by 1;


--transactions
create table transactions
(
    id               int primary key,
    products_id      number references products (id),
    suppliers_id     number references suppliers (id),
    users_id         number references users (id),
    transaction_type nvarchar2(10),
    quantity         number,
    transaction_date timestamp DEFAULT sysdate
);

create sequence transactions_seq start with 1 increment by 1;


----report transaction
create view transactions_report as
select t.id as         transaction_id,
       t.products_id,
       p.category      products_category,
       p.brand         products_brand,
       p.model         products_model,
       p.os            products_os,
       p.has_charger   products_has_charger,
       p.has_headset   products_has_headset,
       p.serial_number products_serial_number,
       p.price         products_price,
       p.quantity      products_count,

       t.suppliers_id,
       s.person_type   suppliers_person_type,
       s.NAME          suppliers_name,
       s.national_id   suppliers_national_id,
       s.postalcode    suppliers_postalcode,
       s.phone_number  suppliers_phone_number,
       s.mobile_number suppliers_mobile_number,

       t.users_id,
       u.national_id   users_national_id,
       u.name          users_name,
       u.family        users_family,

       t.transaction_type,
       t.quantity,
       t.transaction_date


from TRANSACTIONS t -- برای جداول اسم مستعار as ندارد
         join products p on t.products_id = p.id
         join suppliers s on t.suppliers_id = s.id
         join Users u on t.users_id = u.id;







