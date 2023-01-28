create schema if not exists cart;

create table cart.cart (
    id bigserial primary key,
    user_identifier varchar(100) not null,
    date timestamp not null,
    total float not null
);

create table cart.item (
    cart_id bigserial references cart.cart(id),
    product_identifier varchar(100) not null,
    price float not null
);