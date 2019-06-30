create table users (  #用户表
					   username varchar(40) primary key,#账号
					   password varchar(100),	#密码
					   nickname varchar(40),	#昵称
					   email varchar(100),	#邮箱地址
					   sex bit(1) default 0, #性别
					   privilege int default 1,  #权限
					   state int default 0, #激活状态
					   active_code varchar(100), #激活码
                       updatetime timestamp #创建时间
);
create table addr(  #用户收货地址表
					 id int primary key auto_increment,
					 address varchar(255), #收货地址
					 tel varchar(20), #联系方式
                     consignee varchar(100), #收货人
                     u_name varchar(40) references users(username)  #外键, 关联用户表
	);

create table products(  #商品表
						 id int primary key auto_increment,
						 name varchar(40),#商品名称
						 price double,#价格
						 category_1 varchar(40), #商品分类1
						 category_2 varchar(40), #商品分类2
						 pnum int , #商品库存
						 imgurl varchar(255),
						 status bit(1),
						description mediumtext #商品描述
	);
create table orders(  #订单表
					   id int primary key auto_increment,
					   total_prices double,#商品总价
					   total_quanity int,#商品数量
					   paystate int,  #支付状态，0购物车，1下订单没支付，2订单已支付
					   ordertime timestamp, #订单创建时间
					   u_name varchar(40) references users(username),
					   consignee varchar(100),  #收货人
                       tel varchar(20),#电话
					   address varchar(255) #收货地址
	);
create table orderitem(
						  order_id int,
						  product_id int,
						  totalprice double,
						  num int ,
						  primary key(order_id,product_id), #联合主键,两列的值加在一起作为这张表的主键使用
						  foreign key(order_id) references orders(id),
						  foreign key(product_id) references products(id)
);