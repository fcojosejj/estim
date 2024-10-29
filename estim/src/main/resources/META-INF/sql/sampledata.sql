insert into Cliente (nombre, apellidos, dni, email, fechaNac, rol, usuario, psswd, calle, num, ciudad, codPostal, pais) VALUES ('Paco', 'Martinez Soria', '11111111A', 'paco@hotmail.com', '2001-11-29', 'USUARIOS', 'paco123', 'paco123', 'Calle Normandia', '4', 'Jaén', '23300', 'España');
insert into Cliente (nombre, apellidos, dni, email, fechaNac, rol, usuario, psswd, calle, num, ciudad, codPostal, pais) VALUES ('Laura', 'Ortiz Gómez', '22222222B', 'laura@hotmail.com', '2001-01-14', 'USUARIOS', 'laura123', 'laura123', 'Calle Lucrecia', '15', 'Barcelona', '69345', 'España');
insert into Cliente (nombre, apellidos, dni, email, fechaNac, rol, usuario, psswd, calle, num, ciudad, codPostal, pais) VALUES ('Ander', 'Herrera Goikoetxea', '33333333C', 'ander@hotmail.com', '1987-05-24', 'USUARIOS', 'anderherrera', 'anderherrera', 'Calle Lucas Román', '9', 'Sevilla', '17004', 'España');
insert into Cliente (nombre, apellidos, dni, email, fechaNac, rol, usuario, psswd, calle, num, ciudad, codPostal, pais) VALUES ('Admin', 'Administrador Administradorez', '99999999D', 'admin@hotmail.com', '1987-06-24', 'ADMINISTRADORES', 'admin', 'admin', 'Calle Admin', '10', 'Ciudad Admin', '99999', 'España');


insert into Videojuego (nombre, desarrolladora, descripcion, precio, valoracion) VALUES ('Minecraft', 'Mojang', 'Juego de bloques', '20.00', '5');
insert into Videojuego (nombre, desarrolladora, descripcion, precio, valoracion) VALUES ('Lost Ark', 'Smilegate Games', 'MMORPG', '25.00', '4');
insert into Videojuego (nombre, desarrolladora, descripcion, precio, valoracion) VALUES ('League of Legends', 'Rito Games', 'MOBA', '5.00', '3');

insert into Carrito (idCliente, idVideojuego) VALUES ('1', '1');
insert into Carrito (idCliente, idVideojuego) VALUES ('1', '2');
insert into Carrito (idCliente, idVideojuego) VALUES ('2', '1');

