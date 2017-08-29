insert into ingredient_type(ingredient_type)
select possible.ingredient_type from
(select 'ICE_CREAM' ingredient_type union select 'TOPPING') possible
where possible.ingredient_type not in (select ingredient_type from ingredient_type)

;

insert into ingredient(ingredient, ingredient_type, unit_price)
select possible.ingredient, possible.ingredient_type, possible.unit_price from
(
    select 'Vanilla' as ingredient, 'ICE_CREAM' as ingredient_type, 1.5 as unit_price
    union select 'Chocolate' as ingredient, 'ICE_CREAM' as ingredient_type, 1.5 as unit_price
    union select 'Strawberry' as ingredient, 'ICE_CREAM' as ingredient_type, 1.5 as unit_price
    union select 'Caramel' as ingredient, 'TOPPING' as ingredient_type, 0.75 as unit_price
    union select 'Cherry' as ingredient, 'TOPPING' as ingredient_type, 0.25 as unit_price
    union select 'Chocolate' as ingredient, 'TOPPING' as ingredient_type, 0.50 as unit_price
    union select 'Peanuts' as ingredient, 'TOPPING' as ingredient_type, 0.25 as unit_price
    union select 'Sprinkles' as ingredient, 'TOPPING' as ingredient_type, 0.25 as unit_price
) possible
where possible.ingredient not in (select ingredient from ingredient)

;
