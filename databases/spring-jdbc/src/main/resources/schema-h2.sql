create table ingredient_type (
  ingredient_type varchar(50)

  ,constraint PK_ingredient_type primary key(ingredient_type)
);

create table ingredient (
  id int identity
  ,ingredient varchar(100) not null
  ,ingredient_type varchar(50) not null
  ,unit_price decimal(19, 4) not null

  ,constraint PK_ingredient primary key(id)
  ,constraint FK_ingredient_ingredient_type foreign key (ingredient_type) references ingredient_type(ingredient_type)
);

create table purchase (
  id int identity
  ,create_dttm datetime default CURRENT_TIMESTAMP
  ,total_price decimal(19, 4) not null

  ,constraint PK_purchase primary key(id)
);

create table purchase_line_item (
  id int identity
  ,purchase_id int not null
  ,ingredient_id int not null
  ,units int not null

  ,constraint PK_purchase_line_item primary key(id)
  ,constraint FK_purchase_line_item_purchase foreign key (purchase_id) references purchase(id)
  ,constraint FK_purchase_line_item_ingredient foreign key (ingredient_id) references ingredient(id)
);
