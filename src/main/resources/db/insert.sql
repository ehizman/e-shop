set foreign_key_checks=0;

truncate table product;
truncate table feedback;

insert into product(`id`, `name`, `price`, `details`, `currency`)
values(110,'luxury chair', '4500', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'NGN'),
(111,'luxury sofa', '4000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'USD'),
(112,'luxury bench', '6500', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'NGN'),
(113,'luxury table', '8500', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'NGN');

set foreign_key_checks=1;
