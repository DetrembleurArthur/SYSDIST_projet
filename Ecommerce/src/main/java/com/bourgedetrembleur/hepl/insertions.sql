
truncate table category;
truncate table article;
truncate table stock;

insert into category values(1, "book");
insert into category values(2, "video-game");
insert into category values(3, "goodie");


insert into article values(1, "Halo cryptum", 8.5, 1, 1);
insert into article values(2, "Halo primordium", 9, 1, 2);
insert into article values(3, "Halo silentium", 15.0, 1, 3);
insert into article values(4, "Halo Combat Evolved", 40.0, 2, 4);
insert into article values(5, "Halo 2", 45.5, 2, 5);
insert into article values(6, "Halo 3", 50.0, 2, 6);
insert into article values(7, "Halo 4", 50.0, 2, 7);
insert into article values(8, "Halo 5 Guardians", 60.0, 2, 8);
insert into article values(9, "Halo 4 Poster", 4.0, 3, 9);
insert into article values(10,"Halo 3 T-shirt", 8.0, 3, 10);
insert into article values(11,"Halo 2 Mug", 7.5, 3, 11);

insert into stock values(1, 10);
insert into stock values(2, 10);
insert into stock values(3, 10);
insert into stock values(4, 10);
insert into stock values(5, 10);
insert into stock values(6, 10);
insert into stock values(7, 10);
insert into stock values(8, 10);
insert into stock values(9, 10);
insert into stock values(10, 10);
insert into stock values(11, 10);