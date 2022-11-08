create table sh_product_code(
    p_code number(30),
    category_name varchar2(50),
    constraint spc_pk primary key (p_code)
);

create table sh_goods(
    g_idx number(30),
    goods_name varchar(50),
    goods_price number(30),
    regidate date default sysdate,
    p_code number(30) references sh_product_code(p_code),
    constraint sg_pk primary key(g_idx)
);

create table sh_goods_log(
    log_idx number(30),
    goods_name varchar(50),
    goods_idx number(30),
    p_action varchar(50),
    constraint sgl_check check(p_action in('Insert','Delete'))
);

create sequence seq_total_idx
    start with 1
    increment by 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache
;

insert into sh_product_code
values(seq_total_idx.nextval,'°¡Àü');
insert into sh_product_code
values(seq_total_idx.nextval,'µµ¼­');
insert into sh_product_code
values(seq_total_idx.nextval,'ÀÇ·ù');

insert into sh_goods(g_idx, goods_name, goods_price, p_code)
values(seq_total_idx.nextval,'¸ð´ÏÅÍ',200000,1);
insert into sh_goods(g_idx, goods_name, goods_price, p_code)
values(seq_total_idx.nextval,'³ÃÀå°í',1600000,2);
insert into sh_goods(g_idx, goods_name, goods_price, p_code)
values(seq_total_idx.nextval,'¿¡¾îÄÁ',1900000,2);
insert into sh_goods(g_idx, goods_name, goods_price, p_code)
values(seq_total_idx.nextval,'¼¼Å¹±â',2100000,2);
insert into sh_goods(g_idx, goods_name, goods_price, p_code)
values(seq_total_idx.nextval,'»çÇÇ¿£½º',10000,3);
insert into sh_goods(g_idx, goods_name, goods_price, p_code)
values(seq_total_idx.nextval,'ÃÑ±Õ¼è',8000,3);
insert into sh_goods(g_idx, goods_name, goods_price, p_code)
values(seq_total_idx.nextval,'·ÕÆÐµù',370000,4);
insert into sh_goods(g_idx, goods_name, goods_price, p_code)
values(seq_total_idx.nextval,'·¹±ë½º',20000,4);
insert into sh_goods(g_idx, goods_name, goods_price, p_code)
values(seq_total_idx.nextval,'Ã»¹ÙÁö',15000,4);


create or replace procedure ShopUpdateGoods(
    su_name sh_goods.goods_name%type,
    su_price sh_goods.goods_price%type,
    su_code sh_goods.p_code%type,
    su_idx sh_goods.g_idx%type,
    isRow out number
)
is
begin
    update
        sh_goods
    set
        goods_name=su_name,
        goods_price=su_price,
        p_code=su_code
    where
        g_idx = su_idx;
        
    if SQL%found then
        isRow := SQL%rowcount;
        commit;
    else
        isRow := 0;
    end if;
end;
/

create or replace procedure ShopDeleteGoods(
    su_idx sh_goods.g_idx%type,
    isRow out number
)
is
begin
    delete from
        sh_goods
    where
        g_idx = su_idx;
    if SQL%found then
        isRow := SQL%rowcount;
        commit;
    else
        isRow := 0;
    end if;
end;
/

create or replace trigger shop_log_trigger
    after
    INSERT or DELETE
    on sh_goods
    for each row
begin
    if inserting then
        insert into sh_goods_log
        values(
            seq_total_idx.nextval,
            :new.goods_name,
            :new.g_idx,
            'Insert'
        );
    elsif deleting then
        insert into sh_goods_log
        values(
            seq_total_idx.nextval,
            :old.goods_name,
            :old.g_idx,
            'Delete'
        );
    end if;
end;
/

select * from sh_goods_log;