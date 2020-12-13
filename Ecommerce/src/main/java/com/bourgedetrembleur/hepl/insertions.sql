

truncate table item;
truncate table category;
truncate table article;

insert into category values(1, 'book');
insert into category values(2, 'video-game');
insert into category values(3, 'goodie');


insert into article values(1, 'Halo cryptum', 8.5, 10, 1);
insert into article values(2, 'Halo primordium', 9, 10, 1);
insert into article values(3, 'Halo silentium', 15.0, 10, 1);
insert into article values(4, 'Halo Combat Evolved', 40.0, 10, 2);
insert into article values(5, 'Halo 2', 45.5, 10, 2);
insert into article values(6, 'Halo 3', 50.0, 10, 2);
insert into article values(7, 'Halo 4', 50.0, 10, 2);
insert into article values(8, 'Halo 5 Guardians', 60.0, 10, 2);
insert into article values(9, 'Halo 4 Poster', 4.0, 10, 3);
insert into article values(10,'Halo 3 T-shirt', 8.0, 10, 3);
insert into article values(11,'Halo 2 Mug', 7.5, 10, 3);
